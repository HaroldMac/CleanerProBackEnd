package com.cleanerPro.CleanerPro.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cleaner {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date joinDate;

	private String nameFirst, nameLast;
	private String homeAddress, emailAddress, phoneNumber;
	private String profilePicture;
	private double yearlyPay, totalPay, hourlyRate, yearlyTips, totalTips;
	
	@OneToMany(mappedBy = "cleaner", cascade = CascadeType.ALL)
	private Set<Client> clients;
	
}
