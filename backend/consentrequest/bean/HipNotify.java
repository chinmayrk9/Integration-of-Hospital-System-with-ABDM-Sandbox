package com.example.HAD.consentrequest.bean;

public class HipNotify {

	private HipNotification notification;
	
	private String requestId;
	private String timestamp;
	public HipNotification getNotification() {
		return notification;
	}
	public void setNotification(HipNotification notification) {
		this.notification = notification;
	}
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
}
