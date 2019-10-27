package com.cleanerPro.CleanerPro.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Client {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameFirst, nameLast;
	private String homeAddress, emailAddress, phoneNumber;
	private double hourlyRate, minimiumNumberOfHours;
	private int customerDifficultyLevel, cleaningDifficultyLevel;
	private String comments;
	
	@ManyToOne
	@JoinColumn(name="cleaner_id")
	private Cleaner cleaner;

}
