package com.example.HAD.patientregistration.bean;

import java.util.List;

public class Auth {

	private String purpose;
	
	private List<String> modes;

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public List<String> getModes() {
		return modes;
	}

	public void setModes(List<String> modes) {
		this.modes = modes;
	}
}
