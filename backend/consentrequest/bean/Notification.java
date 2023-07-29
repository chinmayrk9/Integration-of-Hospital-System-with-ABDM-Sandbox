package com.example.HAD.consentrequest.bean;

import java.util.List;

public class Notification {
	
	private String status;
	private String consentRequestId;
	
	private List<ConsentArtefacts> consentArtefacts;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConsentRequestId() {
		return consentRequestId;
	}

	public void setConsentRequestId(String consentRequestId) {
		this.consentRequestId = consentRequestId;
	}

	public List<ConsentArtefacts> getConsentArtefacts() {
		return consentArtefacts;
	}

	public void setConsentArtefacts(List<ConsentArtefacts> consentArtefacts) {
		this.consentArtefacts = consentArtefacts;
	}

}
