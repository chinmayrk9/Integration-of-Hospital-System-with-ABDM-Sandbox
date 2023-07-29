package com.example.HAD.patientregistration.bean;

public class PatientRequestDemo {
	
private String requestId="";
	
	private String timestamp= "";
	
	private String transactionId="";
	
	
	private CredentialDemo credential;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public CredentialDemo getCredential() {
		return credential;
	}

	public void setCredential(CredentialDemo credential) {
		this.credential = credential;
	}


}
