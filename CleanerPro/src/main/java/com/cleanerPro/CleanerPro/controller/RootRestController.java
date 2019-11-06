package com.cleanerPro.CleanerPro.controller;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleanerPro.CleanerPro.model.Cleaner;
import com.cleanerPro.CleanerPro.model.Client;
import com.cleanerPro.CleanerPro.repository.CleanerRepository;
import com.cleanerPro.CleanerPro.repository.ClientRepository;
import com.cleanerPro.CleanerPro.repository.ScheduledCleaningRepository;



@RestController
public class RootRestController {
	
	@Autowired
	private CleanerRepository cleanerRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ScheduledCleaningRepository scheduledCleaningRepository;
	
	
	@PostMapping("/api/addScheduledCleaning")
	public void addScheduledCleaningByCleanerId(@RequestParam(name="cleanerId") String cleanertId){
		System.out.println("Trying to add a cleaning here");
	}
	
	/*
	 * Need to add
	 * done! a way to create, read, update and delete a customer (signup, remove account)
	 *       a way to retrieve or change a lost password and send a link via email for changing your email
	 * 
	 * done! a way to create, read, update and delete a client
	 *       a way to create, read, update and delete a scheduled appointment
	 *       a way to send a "i'm about to clean your place" and a "i'm done cleaning your place" email **note** maybe make a html version of this email
	 *   
	 */
	
	//crud for customer
	
	
	@PostMapping("/api/cleaner/signup")
	public void signUp(@RequestParam(name="userName") String userName,@RequestParam(name="password") String pass, @RequestBody Cleaner cleaner){
		System.out.println("Creating a cleaner: " + cleaner.getNameFirst());
		Date now = new Date();
		cleaner.setJoinDate(now);
		cleaner.setClients(null);
		cleanerRepository.save(cleaner);
	}
	
	@GetMapping("/api/cleaner/getCleaner")
	public Cleaner getCleanerById(@RequestParam(name="cleanerId") String cleanerId){
		System.out.println("Getting a cleaner by id: " + cleanerId);
		Cleaner cleaner = new Cleaner();
		return cleaner;
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
		Client client = new Client();
		return client;
	}
	
	@GetMapping("/api/client/getAllClients")
	public List<Client> getClientByCleanerId(@RequestParam(name="cleanerId") Long cleanerId){
		Client client1 = new Client();
		Client client2 = new Client();
		List<Client> clients = new ArrayList<Client>();
		clients.add(client1);
		clients.add(client2);
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
	
	

}
