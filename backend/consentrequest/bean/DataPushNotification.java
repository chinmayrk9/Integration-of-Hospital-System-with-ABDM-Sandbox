package com.example.HAD.consentrequest.bean;

public class DataPushNotification {

	private String requestId;
	
	private String timestamp;
	
	private DataTransferNotification notification;

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

	public DataTransferNotification getNotification() {
		return notification;
	}

	public void setNotification(DataTransferNotification notification) {
		this.notification = notification;
	}
}
