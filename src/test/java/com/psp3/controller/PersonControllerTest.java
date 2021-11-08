package com.psp3.controller;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.psp3.model.Person;
import com.psp3.service.PersonService;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PersonService service;

    @InjectMocks
    PersonController controller;

    @Test
    void testShowAll() throws Exception {
        List<Person> p = new ArrayList<>();
        p.add(new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??"));
        p.add(new Person(2, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??"));
        when(service.findAll()).thenReturn(p);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-people")
                .accept(MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-people"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/list-people.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("people"))
                .andReturn();
    }

    @Test
    void testShowAddPage() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-person");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("person"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/person.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("firstName", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("lastName", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("phoneNumber", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("emailAddress", emptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("password", emptyOrNullString())))
                .andReturn();
    }

    @Test
    void testAdd() throws Exception {
        when(service.add(Mockito.any(Person.class))).thenReturn(new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??"));

        RequestBuilder rb = MockMvcRequestBuilders
                .post("/add-person")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Vardas")
                .param("lastName", "Pavarde")
                .param("phoneNumber", "37067777777")
                .param("emailAddress", "email")
                .param("password", "Password123??")
                .flashAttr("person", new Person());

        mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/list-people"));

        verify(service).add(Mockito.any(Person.class));
    }

    @Test
    void testShowUpdatePage() throws Exception {
        when(service.findById(Mockito.anyLong())).thenReturn(new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??"));

        RequestBuilder ug = MockMvcRequestBuilders.get("/update-person/1");

        MvcResult result = mockMvc.perform(ug)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("person"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/jsp/person.jsp"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("person"))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("firstName", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("lastName", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("phoneNumber", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("emailAddress", notNullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute("person",  hasProperty("password", notNullValue())))
                .andReturn();
    }

    @Test
    void testUpdate() throws Exception {
        when(service.update(Mockito.any(Person.class))).thenReturn(new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/update-person/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "Vardas")
                .param("lastName", "Pavarde")
                .param("phoneNumber", "37067777777")
                .param("emailAddress", "email")
                .param("password", "Password123??")
                .flashAttr("person", new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??"));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/list-people"))
                .andReturn();

        verify(service).update(Mockito.any(Person.class));
    }

    @Test
    void testDelete() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/delete-person/1");

        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isFound()).andExpect(MockMvcResultMatchers.redirectedUrl("/list-people")).andReturn();

        verify(service).deleteById(Mockito.anyLong());
    }
}