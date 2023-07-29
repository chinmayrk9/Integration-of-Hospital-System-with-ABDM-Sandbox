package com.example.HAD.carecontext.bean;

import java.util.List;

public class Patient {

	private String referenceNumber;
	
	private String display;
	
	private List<CareContexts> careContexts;

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<CareContexts> getCareContexts() {
		return careContexts;
	}

	public void setCareContexts(List<CareContexts> careContexts) {
		this.careContexts = careContexts;
	}
}
