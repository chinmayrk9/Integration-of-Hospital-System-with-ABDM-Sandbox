package com.example.HAD.consentrequest.bean;

import java.util.List;



public class ConsentDetail {
	
	private String consentId;
	private String createdAt;
	private Purpose purpose;
	private Patient patient;
	private ConsentManager consentManager;
	
	private Hip hip;
	
	private List<String> hiTypes;
	
	private Permission permission;
	
	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Purpose getPurpose() {
		return purpose;
	}

	public void setPurpose(Purpose purpose) {
		this.purpose = purpose;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ConsentManager getConsentManager() {
		return consentManager;
	}

	public void setConsentManager(ConsentManager consentManager) {
		this.consentManager = consentManager;
	}

	public Hip getHip() {
		return hip;
	}

	public void setHip(Hip hip) {
		this.hip = hip;
	}

	public List<String> getHiTypes() {
		return hiTypes;
	}

	public void setHiTypes(List<String> hiTypes) {
		this.hiTypes = hiTypes;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public List<CareContexts> getCareContexts() {
		return careContexts;
	}

	public void setCareContexts(List<CareContexts> careContexts) {
		this.careContexts = careContexts;
	}

	private List<CareContexts> careContexts;

}
