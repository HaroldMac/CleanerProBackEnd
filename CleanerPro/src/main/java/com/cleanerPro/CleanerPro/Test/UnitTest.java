package com.cleanerPro.CleanerPro.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.cleanerPro.CleanerPro.service.FormVerificationService;

public class UnitTest {

	/*
	 * Test For Phone Number
	 */
	
	@Test
	void isTenDigitNumber() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "1234567890";
		assertTrue(fvs.isValidPhoneNumber(testValue));
	}
	
	@Test
	void isTenDigitNumberAssertFalse() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "123456789";
		assertFalse(fvs.isValidPhoneNumber(testValue));
	}
	
	@Test
	void isTenDigitNumberwith2dashes() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "123-456-7890";
		assertTrue(fvs.isValidPhoneNumber(testValue));
	}
	
	@Test
	void isTenDigitNumberwith2dashesAssertFalse() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "123-4-567890";
		assertFalse(fvs.isValidPhoneNumber(testValue));
	}
	
	@Test
	void isTenDigitNumberwith1dashesandBrackets() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "(123)456-7890";
		assertTrue(fvs.isValidPhoneNumber(testValue));
	}
	
	@Test
	void isTenDigitNumberwith1dashesandMissingFirstBracketsAssertFalse() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "123)456-7890";
		assertFalse(fvs.isValidPhoneNumber(testValue));
	}
	
	@Test
	void isTenDigitNumberwith1dashesandMissingSecondBracketsAssertFalse() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "(123456-7890";
		assertFalse(fvs.isValidPhoneNumber(testValue));
	}
	
	@Test
	void isTenDigitNumberwith0dashesandBrackets() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "(123)4567890";
		assertTrue(fvs.isValidPhoneNumber(testValue));
	}
	/*
	 * Test For Email
	 */
	
	@Test
	void isValidEmail() {
		FormVerificationService fvs = new FormVerificationService();
		String testValue = "test@google.ca";
		assertTrue(fvs.isValidEmail(testValue));
	}
	
	
}
