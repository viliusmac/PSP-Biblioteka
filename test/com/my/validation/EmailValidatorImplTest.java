package com.my.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.my.validation.implimentations.EmailValidatorImpl;

class EmailValidatorImplTest {

    EmailValidatorImpl emailValidator = new EmailValidatorImpl();

    @Test
    void checkEmailSymbolTestSuccess() {
        assertTrue(emailValidator.checkEmailSymbol("test@gmail.com"));
    }

    @Test
    void checkEmailSymbolTestFail() {
        assertFalse(emailValidator.checkEmailSymbol("testgmail.com"));
    }

    @Test
    void checkEmailTestSuccess() {
        assertTrue(emailValidator.checkEmail("test@gmail.com"));
    }

    @Test
    void checkEmailTestFail() {
        assertFalse(emailValidator.checkEmail("¶test£@gmail.com"));
    }

    @Test
    void checkEmailDomainAndTLDSuccess() {
        assertTrue(emailValidator.checkDomainAndTLD("test@gmail.com"));
    }

    @Test
    void checkEmailDomainAndTLDFail() {
        assertFalse(emailValidator.checkDomainAndTLD("test@"));
    }
}
