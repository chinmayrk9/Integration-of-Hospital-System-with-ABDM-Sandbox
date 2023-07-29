package com.example.HAD.consentrequest.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConsentRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String abhaId;
	
	private String doctorId;
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	private String patientName;
	
	private String requestStatus;
	
	private String createdOn;
	
	private String grantedOn;
	
	private String expiryOn;
	
	private String consentId;
	
	private String requestId;
	
	private String transactionId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getAbhaId() {
		return abhaId;
	}

	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getGrantedOn() {
		return grantedOn;
	}

	public void setGrantedOn(String grantedOn) {
		this.grantedOn = grantedOn;
	}

	public String getExpiryOn() {
		return expiryOn;
	}

	public void setExpiryOn(String expiryOn) {
		this.expiryOn = expiryOn;
	}
	
}
