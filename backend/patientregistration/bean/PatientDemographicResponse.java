package com.example.HAD.patientregistration.bean;

public class PatientDemographicResponse {
	private String requestId="";
	
	private String timestamp= "";
	
	private String error;
	

	
	private Resp resp;
	
	private Auth1 auth;

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

	public String getError() {
		return error;
	}

	public Auth1 getAuth() {
		return auth;
	}

	public void setAuth(Auth1 auth) {
		this.auth = auth;
	}

	public void setError(String error) {
		this.error = error;
	}

	

	public Resp getResp() {
		return resp;
	}

	public void setResp(Resp resp) {
		this.resp = resp;
	}

	
	
}
