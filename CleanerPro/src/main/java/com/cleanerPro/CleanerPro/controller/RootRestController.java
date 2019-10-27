package com.cleanerPro.CleanerPro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleanerPro.CleanerPro.model.Cleaner;
import com.cleanerPro.CleanerPro.model.Client;

@RestController
public class RootRestController {
	
	@GetMapping("/api/getCleaner")
	public Cleaner getCleanerById(@RequestParam(name="cleanerId") String cleanerId){
		Cleaner cleaner = new Cleaner();
		return cleaner;
	}
	
	@GetMapping("/api/getClient")
	public Client getClientById(@RequestParam(name="clientId") String clientId){
		Client client = new Client();
		return client;
	}

}
