package com.cleanerPro.CleanerPro.service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class FormVerificationService {

	public boolean isValidName(String name) {
		if (name.length() > 0) return true;
		return false;
	}
	
	public boolean isValidEmail(String email) {
		try {
			InternetAddress emailAddress = new InternetAddress(email);
			emailAddress.validate();
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		
		String pattern1 = "\\d{10}";  							//matches 10 digits
		String pattern2 = "(?:\\d{3}-){2}\\d{4}";  				//matches 3digits- 3digits- 4digits
		String pattern3 = "\\(\\d{3}\\)\\d{3}-?\\d{4}";  		//matches (3digits)- 3digits- 4digits
		if ((phoneNumber.matches(pattern1)) || (phoneNumber.matches(pattern2)) || (phoneNumber.matches(pattern3))) return true;
		return false;
	}
	
	
}
