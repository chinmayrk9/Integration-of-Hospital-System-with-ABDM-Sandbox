package com.example.HAD.consentrequest.bean;

public class HipNotification {

	private ConsentDetail consentDetail;
	private String status;
	private String signature;
	
	private String grantAcknowledgement;
	public ConsentDetail getConsentDetail() {
		return consentDetail;
	}
	public void setConsentDetail(ConsentDetail consentDetail) {
		this.consentDetail = consentDetail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getGrantAcknowledgement() {
		return grantAcknowledgement;
	}
	public void setGrantAcknowledgement(String grantAcknowledgement) {
		this.grantAcknowledgement = grantAcknowledgement;
	}
}
