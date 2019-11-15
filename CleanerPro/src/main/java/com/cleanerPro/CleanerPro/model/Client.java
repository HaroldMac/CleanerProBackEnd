package com.cleanerPro.CleanerPro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin
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

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getNameLast() {
		return nameLast;
	}

	public void setNameLast(String nameLast) {
		this.nameLast = nameLast;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double getMinimiumNumberOfHours() {
		return minimiumNumberOfHours;
	}

	public void setMinimiumNumberOfHours(double minimiumNumberOfHours) {
		this.minimiumNumberOfHours = minimiumNumberOfHours;
	}

	public int getCustomerDifficultyLevel() {
		return customerDifficultyLevel;
	}

	public void setCustomerDifficultyLevel(int customerDifficultyLevel) {
		this.customerDifficultyLevel = customerDifficultyLevel;
	}

	public int getCleaningDifficultyLevel() {
		return cleaningDifficultyLevel;
	}

	public void setCleaningDifficultyLevel(int cleaningDifficultyLevel) {
		this.cleaningDifficultyLevel = cleaningDifficultyLevel;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Cleaner getCleaner() {
		return cleaner;
	}

	public void setCleaner(Cleaner cleaner) {
		this.cleaner = cleaner;
	}


}
