package com.psp3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.psp3.model.Person;
import com.psp3.service.PersonService;

@Controller
public class PersonController {

    PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/list-people")
    public String showAll(ModelMap model) {
        model.put("people", service.findAll());
        return "list-people";
    }

    @GetMapping("/add-person")
    public String showAddPage(ModelMap model, @ModelAttribute("person") Person person, BindingResult result) {
        model.addAttribute("person", new Person());
        return "person";
    }

    @PostMapping("/add-person")
    public String add(ModelMap model, @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person";
        }
        if (service.add(person) == null) {
            return "redirect:/error";
        }
        return "redirect:/list-people";
    }

    @GetMapping("/update-person/{personId}")
    public String showUpdatePage(ModelMap model, @PathVariable int personId) {
        model.addAttribute("person", service.findById(personId));
        return "person";
    }

    @PostMapping("/update-person/{id}")
    public String update(ModelMap model, @ModelAttribute("person") Person person, BindingResult result) {
        if (result.hasErrors()) {
            return "person";
        }
        if (service.update(person) == null) {
            return "redirect:/error";
        }
        return "redirect:/list-people";
    }

    @GetMapping("/delete-person/{id}")
    public String delete(@PathVariable long id) {
        service.deleteById(id);
        return "redirect:/list-people";
    }

}
