package com.example.HAD.consentrequest.bean;

import com.example.HAD.carecontext.bean.Resp;

public class OnFetchResponse {
	private String requestId;
	
	private String timestamp;
	
	private OnFetchConsent consent;
	
	private String error;
	
	private Resp resp;

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

	public OnFetchConsent getConsent() {
		return consent;
	}

	public void setConsent(OnFetchConsent consent) {
		this.consent = consent;
	}

	public String getError() {
		return error;
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
