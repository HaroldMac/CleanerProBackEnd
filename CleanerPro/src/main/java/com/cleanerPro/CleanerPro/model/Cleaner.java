package com.cleanerPro.CleanerPro.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Cleaner {
	
	@Id @GeneratedValue
	private Long id;
	private Date joinDate;

	private String nameFirst, nameLast;
	private String homeAddress, emailAddress, phoneNumber;
	private String profilePicture;
	private double yearlyPay, totalPay, hourlyRate, yearlyTips, totalTips;
	
	private Set<Client> clients;
	
}
