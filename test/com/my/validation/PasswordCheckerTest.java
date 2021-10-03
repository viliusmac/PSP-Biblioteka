package com.my.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.my.validation.implimentations.PasswordCheckerImpl;

class PasswordCheckerTest {

	PasswordCheckerImpl passwordChecker = new PasswordCheckerImpl();

	@Test
	void checkLengthTestSuccess() {
		assertTrue(passwordChecker.checkLength("Asdef112"));
	}

	@Test
	void checkLengthTestFail() {
		assertFalse(passwordChecker.checkLength("Asd112"));
	}

	@Test
	void checkUpperCaseTestSuccess() {
		assertTrue(passwordChecker.checkUpperCase("Asdef112"));
	}

	@Test
	void checkUpperCaseTestFail() {
		assertFalse(passwordChecker.checkUpperCase("asdef112"));
	}

	@Test
	void checkSpecialSymbolTestSuccess() {
		assertTrue(passwordChecker.checkSpecialSymbol("Asdef112@"));
	}

	@Test
	void checkSpecialSymbolTestFail() {
		assertFalse(passwordChecker.checkSpecialSymbol("Asdef112"));
	}
}
