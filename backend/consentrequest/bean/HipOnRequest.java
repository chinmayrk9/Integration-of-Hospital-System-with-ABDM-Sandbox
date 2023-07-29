package com.example.HAD.consentrequest.bean;

import com.example.HAD.carecontext.bean.Resp;

public class HipOnRequest {

	private String requestId;
	
	private String timestamp;
	
	private HnRequest hiRequest;
	
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

	public HnRequest getHiRequest() {
		return hiRequest;
	}

	public void setHiRequest(HnRequest hiRequest) {
		this.hiRequest = hiRequest;
	}

	public Resp getResp() {
		return resp;
	}

	public void setResp(Resp resp) {
		this.resp = resp;
	}
}
