package com.example.HAD.consentrequest.bean;

public class DataTransferNotification {
	
	private String consentId;
	
	private String transactionId;
	
	private String doneAt;
	
	private Notifier notifier;
	
	private StatusNotification statusNotification;

	public String getConsentId() {
		return consentId;
	}

	public void setConsentId(String consentId) {
		this.consentId = consentId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDoneAt() {
		return doneAt;
	}

	public void setDoneAt(String doneAt) {
		this.doneAt = doneAt;
	}

	public Notifier getNotifier() {
		return notifier;
	}

	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}

	public StatusNotification getStatusNotification() {
		return statusNotification;
	}

	public void setStatusNotification(StatusNotification statusNotification) {
		this.statusNotification = statusNotification;
	}

}
