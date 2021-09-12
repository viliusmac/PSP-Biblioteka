package com.biblioteka;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PasswordValidatorTest {

    PasswordValidator passwordValidator = new PasswordValidator();

    @Test
    void validatePassword_ValidPassword() {
        String password = "Password1$";

        assertTrue(passwordValidator.validatePassword(password));
    }

    @Test
    void validatePassword_PasswordTooShort() {
        String password = "pass$";

        assertFalse(passwordValidator.validatePassword(password));
    }

    @Test
    void validatePassword_HasNoUppercaseChars() {
        String password = "password1$";

        assertFalse(passwordValidator.validatePassword(password));
    }

    @Test
    void validatePassword_HasNoSpecialChars() {
        String password = "Password11";

        assertFalse(passwordValidator.validatePassword(password));
    }
}