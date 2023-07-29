package com.example.HAD.config;

import com.example.HAD.carecontext.bean.Resp;

public class ConsentResponse {

	private String requestId;
	
	private String timestamp;
	
	private Resp resp;
	
	private String error;
	
	private ConsentRequest consentRequest;

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

	public Resp getResp() {
		return resp;
	}

	public void setResp(Resp resp) {
		this.resp = resp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ConsentRequest getConsentRequest() {
		return consentRequest;
	}

	public void setConsentRequest(ConsentRequest consentRequest) {
		this.consentRequest = consentRequest;
	}
}
