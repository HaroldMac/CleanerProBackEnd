package com.cleanerPro.CleanerPro.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PasswordToken {

	@Id @GeneratedValue
	private Long id;
	
	private Long userId;
	
	private Date dateCreated;
	private String token, emailAddress;
	private boolean isStillValid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isStillValid() {
		return isStillValid;
	}
	public void setStillValid(boolean isStillValid) {
		this.isStillValid = isStillValid;
	}
		
}
