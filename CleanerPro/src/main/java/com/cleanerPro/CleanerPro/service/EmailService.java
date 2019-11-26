package com.cleanerPro.CleanerPro.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cleanerPro.CleanerPro.model.Cleaner;
import com.cleanerPro.CleanerPro.model.Client;
import com.cleanerPro.CleanerPro.model.PasswordToken;
import com.cleanerPro.CleanerPro.model.ScheduledCleaning;
import com.cleanerPro.CleanerPro.model.User;



@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendCleaningComplete(Cleaner cleaner, Client client, ScheduledCleaning scheduledCleaning) {
		System.out.println("I just finished cleaning your place");
		String subject = cleaner.getNameFirst() + " " + cleaner.getNameLast() + " appointment complete";
		String body = cleaner.getNameFirst() + " " + cleaner.getNameLast() + " has finished cleaning";
		FormVerificationService fvService = new FormVerificationService();
		if (fvService.isValidEmail(client.getEmailAddress())) {
			try {
				this.send(client.getEmailAddress(), subject, body);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendAppointmentBooked(Cleaner cleaner, Client client, ScheduledCleaning scheduledCleaning) {
		System.out.println("I just finished cleaning your place");
		String subject = cleaner.getNameFirst() + " " + cleaner.getNameLast() + " cleaning appointment booked";
		String body = cleaner.getNameFirst() + " " + cleaner.getNameLast() + " has finished cleaning";
		FormVerificationService fvService = new FormVerificationService();
		if (fvService.isValidEmail(client.getEmailAddress())) {
			try {
				this.send(client.getEmailAddress(), subject, body);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	public void sendBookingOfScheduledCleaning(Client client, ScheduledCleaning scheduledCleaning) {
		FormVerificationService fvService = new FormVerificationService();
		String subject = "FauxPho confirmation Order";
		String body = "Your confirmation number is " + client.getNameFirst() + "\n Please have order number ready on arrival.";
		body += "\n\n Thank you for interest in this application. \nPlease feel free to contact me at harold.macdonald52@gmail.com for any questions.";
		if (fvService.isValidEmail(client.getEmailAddress())) {
			try {
				this.send(client.getEmailAddress(), subject, body);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	*/
	
	public void send(String to, String subject, String body) throws MessagingException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setFrom("no-reply@hmps.ca");
		mail.setSubject(subject);
		mail.setText(body);
		
		javaMailSender.send(mail);
	}
	
	public void sendMime(String to, String subject, String body) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
	         
		helper.setTo(to);
		helper.setText(body);
		helper.setSubject(subject);
		helper.setFrom("no-reply@hmps.ca");
	}
	
	public void sendSignUpConfirmationEmail(PasswordToken token) {
		String subject = "CleanerPro signup confirmation";
		String link = "http://localhost:4200/confirmSignUp?email=" + token.getEmailAddress() + "&token=" + token.getToken();
		
		String body = "Click the link to confirm it was you. \n\n" + link;
		FormVerificationService fvService = new FormVerificationService();
		if (fvService.isValidEmail(token.getEmailAddress())) {
			try {
				this.send(token.getEmailAddress(), subject, body);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendForgottenPasswordEmail(PasswordToken token) {
		String subject = "CleanerPro forgotten Password";
		String link = "http://localhost:4200/ForgottenPassword?email="+ token.getEmailAddress() + "&token=" + token.getToken();
		
		String body = "Click the link to confirm it was you. \n\n" + link;
		FormVerificationService fvService = new FormVerificationService();
		if (fvService.isValidEmail(token.getEmailAddress())) {
			try {
				this.send(token.getEmailAddress(), subject, body);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
