package com.example.HAD.consentrequest.bean;

import java.util.List;

public class Consent {

	private Purpose purpose;
	
	private Patient patient;
	
	private Hiu hiu;
	
	private Requester requester;
	
	private List<String> hiTypes;
	
	private Permission permission;

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

	public Hiu getHiu() {
		return hiu;
	}

	public void setHiu(Hiu hiu) {
		this.hiu = hiu;
	}

	public Requester getRequester() {
		return requester;
	}

	public void setRequester(Requester requester) {
		this.requester = requester;
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
	
}
