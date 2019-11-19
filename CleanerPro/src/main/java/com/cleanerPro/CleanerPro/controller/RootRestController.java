package com.cleanerPro.CleanerPro.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cleanerPro.CleanerPro.model.Cleaner;
import com.cleanerPro.CleanerPro.model.Client;
import com.cleanerPro.CleanerPro.model.ScheduledCleaning;
import com.cleanerPro.CleanerPro.repository.CleanerRepository;
import com.cleanerPro.CleanerPro.repository.ClientRepository;
import com.cleanerPro.CleanerPro.repository.ScheduledCleaningRepository;
import com.cleanerPro.CleanerPro.service.EmailService;



@RestController
@CrossOrigin
public class RootRestController {
	
	@Autowired
	private CleanerRepository cleanerRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
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
	
	//crud for customer
	
	@GetMapping("/api/cleaner/signin")
	public Cleaner signIn(@RequestParam(name="email") String email,@RequestParam(name="password") String pass){
		System.out.println("signing a cleaner: " + email);
		Cleaner cleaner = cleanerRepository.getOne(new Long(1));
		cleaner.setClients(null);
		return cleaner;
	}
	
	
	@PostMapping("/api/cleaner/signup")
	//public ResponseEntity  signUp(@RequestParam(name="email") String email,@RequestParam(name="password") String pass){
	public void signUp(@RequestParam(name="email") String email,@RequestParam(name="password") String pass){
		System.out.println("Creating a cleaner: " + email);
		Cleaner cleaner = new Cleaner();
		cleaner.setEmailAddress(email);
		Date now = new Date();
		cleaner.setJoinDate(now);
		cleaner.setClients(null);
		//cleanerRepository.save(cleaner);
		 //return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@GetMapping("/api/cleaner/getCleaner")
	public Cleaner getCleanerById(@RequestParam(name="cleanerId") Long cleanerId){
		System.out.println("Getting a cleaner by id: " + cleanerId);
		return cleanerRepository.getOne(cleanerId);
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
