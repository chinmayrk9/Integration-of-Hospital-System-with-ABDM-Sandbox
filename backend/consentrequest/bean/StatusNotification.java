package com.example.HAD.consentrequest.bean;

import java.util.List;

public class StatusNotification {

	private String sessionStatus;
	
	private String hipId;
	
	private  List<StatusResponses> statusResponses;

	public String getSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	public String getHipId() {
		return hipId;
	}

	public void setHipId(String hipId) {
		this.hipId = hipId;
	}

	public List<StatusResponses> getStatusResponses() {
		return statusResponses;
	}

	public void setStatusResponses(List<StatusResponses> statusResponses) {
		this.statusResponses = statusResponses;
	}
}
