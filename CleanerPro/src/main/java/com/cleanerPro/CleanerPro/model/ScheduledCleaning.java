package com.cleanerPro.CleanerPro.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ScheduledCleaning {
	
	@Id @GeneratedValue
	private Long id;
	
	private Date date;
	private Long cleanerId, clientId;
	private String notesForClient, notesForCleaner;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getCleanerId() {
		return cleanerId;
	}
	public void setCleanerId(Long cleanerId) {
		this.cleanerId = cleanerId;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getNotesForClient() {
		return notesForClient;
	}
	public void setNotesForClient(String notesForClient) {
		this.notesForClient = notesForClient;
	}
	public String getNotesForCleaner() {
		return notesForCleaner;
	}
	public void setNotesForCleaner(String notesForCleaner) {
		this.notesForCleaner = notesForCleaner;
	} 

}
