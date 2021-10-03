package com.my.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.my.validation.implimentations.PhoneValidatorImpl;

public class PhoneValidatorTest {

	PhoneValidatorImpl phoneValidator = new PhoneValidatorImpl();

	@Test
	void checkPhoneNumberTestSuccess() {
		assertTrue(phoneValidator.checkPhoneNumber("+37062847379"));
	}

	@Test
	void checkPhoneNumberTestFail() {
		assertFalse(phoneValidator.checkPhoneNumber("+3706284737R"));
	}

	// tikrina, kai reikia pakeisti is 8 i +370
	@Test
	void checkFirstNumberWithChangeTest() {
		assertEquals("+37062847379", phoneValidator.checkFirstNumber("862847379"));
	}

	// tikrina, kai nereikia pakeisti is 8 i +370
	@Test
	void checkFirstNumberWithoutChangeTest() {
		assertEquals("+37062847379", phoneValidator.checkFirstNumber("+37062847379"));
	}

	// ar eina pagal prideta prefixa (papildomai sukurtas testas)
	@Test
	void checkIfFitsPrefix() {
		phoneValidator.addPhonePrefix(12, "+550");
		assertTrue(phoneValidator.checkPhoneNumber("+55062847379"));
	}
}
