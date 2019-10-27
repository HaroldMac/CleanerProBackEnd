package com.cleanerPro.CleanerPro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Client {
	
	@Id @GeneratedValue
	private Long id;
	private String nameFirst, nameLast;
	private String homeAddress, emailAddress, phoneNumber;
	private double hourlyRate, minimiumNumberOfHours;
	private int customerDifficultyLevel, cleaningDifficultyLevel;
	private String comments;
	
	//private Cleaner cleaner;

}
