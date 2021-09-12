package com.biblioteka;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailValidatorTest {

    EmailValidator emailValidator = new EmailValidator();

    @Test
    public void validateEmail_ValidEmail() {
        String email = "labas@pastas.lt";

        assertTrue(emailValidator.validateEmail(email));
    }

    @Test
    public void validateEmail_NoAtSign() {
        String email = "labaspastas.lt";

        assertFalse(emailValidator.validateEmail(email));
    }

    @Test
    public void validateEmail_HasInvalidChars() {
        String email = "l@bas@pastas.lt";

        assertFalse(emailValidator.validateEmail(email));
    }

    @Test
    public void validateEmail_HasInvalidTLD() {
        String email = "labas@pastas";

        assertFalse(emailValidator.validateEmail(email));
    }

    @Test
    public void validateEmail_HasInvalidDomain() {
        String email = "labas@.lt";

        assertFalse(emailValidator.validateEmail(email));
    }
}