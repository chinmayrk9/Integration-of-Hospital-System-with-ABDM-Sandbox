package com.example.HAD.carecontext.bean;

public class CareContextResponse {

	private Acknowledgement acknowledgement;
	
	private String error;
	
	private String requestId;
	
	private Resp resp;
	
	private String timestamp;
	
	

	public Acknowledgement getAcknowledgement() {
		return acknowledgement;
	}

	public void setAcknowledgement(Acknowledgement acknowledgement) {
		this.acknowledgement = acknowledgement;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Resp getResp() {
		return resp;
	}

	public void setResp(Resp resp) {
		this.resp = resp;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
}
