package com.cleanerPro.CleanerPro.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleanerPro.CleanerPro.model.Cleaner;
import com.cleanerPro.CleanerPro.model.Client;
import com.cleanerPro.CleanerPro.model.PasswordToken;
import com.cleanerPro.CleanerPro.model.ScheduledCleaning;
import com.cleanerPro.CleanerPro.model.User;
import com.cleanerPro.CleanerPro.repository.CleanerRepository;
import com.cleanerPro.CleanerPro.repository.ClientRepository;
import com.cleanerPro.CleanerPro.repository.PasswordTokenRepository;
import com.cleanerPro.CleanerPro.repository.ScheduledCleaningRepository;
import com.cleanerPro.CleanerPro.repository.UserRepository;
import com.cleanerPro.CleanerPro.service.EmailService;



@RestController
@CrossOrigin
public class RootRestController {
	
	@Autowired
	private CleanerRepository cleanerRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordTokenRepository passwordTokenRepository;
	
	@Autowired
	private ScheduledCleaningRepository scheduledCleaningRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	
	
	/*
	 * Need to add
	 * done! a way to create, read, update and delete a customer (signup, remove account)
	 *       a way to retrieve or change a lost password and send a link via email for changing your email
	 * 
	 * done! a way to create, read, update and delete a client
	 * done! a way to create, read, update and delete a scheduled appointment
	 * done! a way to send a "i'm about to clean your place" and a "i'm done cleaning your place" email **note** maybe make a html version of this email
	 *   
	 */
	
	@GetMapping("/api/cleaner/signin")
	public Cleaner signIn(@RequestParam(name="email") String email,@RequestParam(name="password") String pass){
		
		//Find the cleaner
		List<Cleaner> cleaners = cleanerRepository.findAll();
		for (Cleaner cleaner : cleaners) {
			//confirm email address and password match
			if ((cleaner.getEmailAddress().equals(email)) && (cleaner.getUser().getPassword().equals(pass))){
				cleaner.setClients(null);
				cleaner.setUser(null);
				return cleaner;
			}
		}
		return null;
	}
	
	@PostMapping("/api/cleaner/signup")
	//public ResponseEntity  signUp(@RequestParam(name="email") String email,@RequestParam(name="password") String pass){
	public void signUp(@RequestParam(name="email") String emailAddress, @RequestParam(name="password") String password){
		System.out.println("Creating a cleaner: " + emailAddress + " with password of " + password);
		
		//check if user exists first and if they do send error message

		//create cleaner
		Cleaner cleaner = new Cleaner();
		cleaner.setEmailAddress(emailAddress);
		
		
		//create user
		Date creationDate = new Date();
		User user = new User();
		user.setPassword(password);
		user.setCreationDate(creationDate);
		user.setActive(false);
		
		user.setCleaner(cleaner);
		cleaner.setUser(user);
		
		userRepository.save(user);
		
		//create password token
		PasswordToken passwordToken = new PasswordToken();
		passwordToken.setEmailAddress(emailAddress);
		passwordToken.setDateCreated(creationDate);
		passwordToken.setToken("123456");
		passwordToken.setStillValid(true);
		passwordToken.setUserId(user.getId());
		passwordTokenRepository.save(passwordToken);
		
		//send confirmation email
		emailService.sendSignUpConfirmationEmail(passwordToken);
	}
	
	@GetMapping("/api/cleaner/confirmSignUp")
	public void confirmSignUp(@RequestParam(name="email") String email, @RequestParam(name="token") String token){
		//find password token
		List<PasswordToken> passwordTokens = passwordTokenRepository.findAll();
		for (PasswordToken passwordToken : passwordTokens) {
			if ((passwordToken.getToken().equals(token) && (passwordToken.getEmailAddress().equals(email)))) {
				
				//set user to active
				User user = userRepository.getOne(passwordToken.getUserId());
				user.setActive(true);
				userRepository.save(user);
				
				//deactivate token
				passwordToken.setStillValid(false);
				passwordTokenRepository.save(passwordToken);
			}
		}
	}
		
	@GetMapping("/api/cleaner/forgot")
	public void forgotPasswordEmailRequest(@RequestParam(name="email") String email) {

		//confirm email exists to a user
		List<Cleaner> cleaners = cleanerRepository.findAll();
		for (Cleaner cleaner : cleaners) {
			if (cleaner.getEmailAddress().equals(email)) {
				//create password token
				PasswordToken passwordToken = new PasswordToken();
				passwordToken.setEmailAddress(email);
				passwordToken.setDateCreated(new Date());
				passwordToken.setToken("123456");
				passwordToken.setStillValid(true);
				passwordToken.setUserId(cleaner.getId());
				passwordTokenRepository.save(passwordToken);
				
				//send email
				emailService.sendForgottenPasswordEmail(passwordToken);
			}
		}

	}
	
	@GetMapping("/api/cleaner/updatePassword")
	public void forgotPasswordPasswordUpdate(@RequestParam(name="email") String email, @RequestParam(name="password") String password, @RequestParam(name="token") String token) {		
		//find password token
		List<PasswordToken> passwordTokens = passwordTokenRepository.findAll();
		for (PasswordToken passwordToken : passwordTokens) {
			if ((passwordToken.getToken().equals(token) && (passwordToken.getEmailAddress().equals(email)))) {
				
				//set user to update password
				User user = userRepository.getOne(passwordToken.getUserId());
				user.setPassword(password);
				userRepository.save(user);
				
				//deactivate token
				passwordToken.setStillValid(false);
				passwordTokenRepository.save(passwordToken);
			}
		}
	}
	
	//crud for customer
	
	@GetMapping("/api/cleaner/getCleaner")
	public Cleaner getCleanerById(@RequestParam(name="cleanerId") Long cleanerId){
		System.out.println("Getting a cleaner by id: " + cleanerId);
		User user = userRepository.getOne(cleanerId);
		//checks if the user exists and 
		if (user != null || user.isActive()) {
			return cleanerRepository.getOne(cleanerId);
		}
		return null;
	}
	
	@PostMapping("/api/cleaner/update")
	public void updateCleaner(@RequestBody Cleaner cleaner){
		System.out.println("Updating a cleaner: " + cleaner.getNameFirst());
		cleanerRepository.save(cleaner);
	}
	
	
	@PostMapping("/api/cleaner/deleteAccount")
	//public void deleteAcct(@RequestParam(name="userName") String userName,@RequestParam(name="password") String pass, @RequestBody Cleaner cleaner){
	public void deleteAcct(@RequestBody Cleaner cleaner){
		System.out.println("Deleting a cleaner: " + cleaner.getNameFirst());
		cleanerRepository.delete(cleaner);;
	}
		
	//Client crud
	
	@PostMapping("/api/client/create")
	public void createClient(@RequestParam(name="cleanerId") Long cleanerId, @RequestBody Client client){
		System.out.println("Creating a client: " + client.getNameFirst());		
		Cleaner cleaner = cleanerRepository.getOne(cleanerId);
		client.setCleaner(cleaner);
		clientRepository.save(client);
		
	}
	
	@GetMapping("/api/client/getClient")
	public Client getClientById(@RequestParam(name="clientId") Long clientId){
		System.out.println("Getting a client: " + clientId);
		return clientRepository.getOne(clientId);
	}
	
	@GetMapping("/api/client/getAllClients")
	public List<Client> getClientByCleanerId(@RequestParam(name="cleanerId") Long cleanerId){
		System.out.println("Getting all clients");
		List<Client> clients = clientRepository.findAll();
		clients.forEach(client -> client.setCleaner(null));
		return clients;
	}
	
	@PostMapping("/api/client/update")
	public void updateClient(@RequestBody Client client){
		System.out.println("Updating a client: " + client.getNameFirst());
		clientRepository.save(client);
	}
	
	
	@PostMapping("/api/client/deleteAccount")
	public void deleteClient(@RequestBody Client client){
		System.out.println("Deleting a cleaner: " + client.getNameFirst());
		clientRepository.delete(client);
	}
	
	//Scheduled Cleaning CRUD
	
	@PostMapping("/api/ScheduledCleaning/create")
	public void createScheduledCleaning(@RequestParam(name="cleanerId") Long cleanerId, @RequestParam(name="clientId") Long clientId, ScheduledCleaning scheduledCleaning){
		System.out.println("Creating a client: " + scheduledCleaning.getId());		
		scheduledCleaning.setCleanerId(cleanerId);
		scheduledCleaning.setClientId(clientId);
		scheduledCleaningRepository.save(scheduledCleaning);
	}
	
	@GetMapping("/api/ScheduledCleaning/getScheduledCleaning")
	public ScheduledCleaning getScheduledCleaningById(@RequestParam(name="scheduledCleaningId") Long scheduledCleaningId){
		System.out.println("Get a scheduled cleaning: " + scheduledCleaningId);
		return scheduledCleaningRepository.getOne(scheduledCleaningId);
		
	}
	
	@GetMapping("/api/ScheduledCleaning/getAllScheduledCleanings")
	public List<ScheduledCleaning> getAllScheduledCleaningsByCleanerId(@RequestParam(name="cleanerId") Long cleanerId){
		System.out.println("Get all scheduled cleanings");
		List<ScheduledCleaning> scheduledCleaning = scheduledCleaningRepository.findAll();
		
		return scheduledCleaning;
		
	}
	
	@PostMapping("/api/ScheduledCleaning/update")
	public void updateScheduledCleaning(@RequestBody ScheduledCleaning scheduledCleaning){
		System.out.println("Updating a client: " + scheduledCleaning.getId());
		scheduledCleaningRepository.save(scheduledCleaning);
	}
	
	
	@PostMapping("/api/ScheduledCleaning/deleteAccount")
	public void deleteScheduledCleaning(@RequestBody ScheduledCleaning scheduledCleaning){
		System.out.println("Deleting a cleaner: " + scheduledCleaning.getId());
		scheduledCleaningRepository.delete(scheduledCleaning);
	}
	
	
	
	//Send Scheduled Cleaning notification email
	@PostMapping("/api/ScheduledCleaning/sendScheduledCleaningNotification")
	public void sendScheduledCleaningNotification(ScheduledCleaning scheduledCleaning){
		System.out.println("Sending scheduled cleaning notification: " + scheduledCleaning.getId());
		Cleaner cleaner = cleanerRepository.getOne(scheduledCleaning.getCleanerId());
		Client client = clientRepository.getOne(scheduledCleaning.getClientId());
		emailService.sendAppointmentBooked(cleaner, client, scheduledCleaning);
	}
	
	//Send Scheduled Cleaning completed notification email
	@PostMapping("/api/ScheduledCleaning/sendScheduledCleaningCompleteNotification")
	public void sendScheduledCleaningCompleteNotification(ScheduledCleaning scheduledCleaning){
		System.out.println("Sending scheduled cleaning notification: " + scheduledCleaning.getId());
		Cleaner cleaner = cleanerRepository.getOne(scheduledCleaning.getCleanerId());
		Client client = clientRepository.getOne(scheduledCleaning.getClientId());
		emailService.sendCleaningComplete(cleaner, client, scheduledCleaning);
	}
	
	
}
