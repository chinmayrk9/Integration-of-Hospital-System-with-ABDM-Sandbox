package com.example.HAD.consentrequest.bean;

public class HiRequest {

	private String dataPushUrl;
	
	private DateRange dateRange;
	
	private ConsentId consent;
	
	private KeyMaterial keyMaterial;

	public String getDataPushUrl() {
		return dataPushUrl;
	}

	public void setDataPushUrl(String dataPushUrl) {
		this.dataPushUrl = dataPushUrl;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

	public ConsentId getConsent() {
		return consent;
	}

	public void setConsent(ConsentId consent) {
		this.consent = consent;
	}

	public KeyMaterial getKeyMaterial() {
		return keyMaterial;
	}

	public void setKeyMaterial(KeyMaterial keyMaterial) {
		this.keyMaterial = keyMaterial;
	}
}
