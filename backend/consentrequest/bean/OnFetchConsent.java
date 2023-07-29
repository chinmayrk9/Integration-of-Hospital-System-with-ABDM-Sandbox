package com.example.HAD.consentrequest.bean;

public class OnFetchConsent {
	
	private String status;
	
	private OnFetchConsentDetail consentDetail;
	
	private String signature;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OnFetchConsentDetail getConsentDetail() {
		return consentDetail;
	}

	public void setConsentDetail(OnFetchConsentDetail consentDetail) {
		this.consentDetail = consentDetail;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
