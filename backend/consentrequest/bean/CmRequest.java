package com.example.HAD.consentrequest.bean;

public class CmRequest {
	
	private String transactionId;
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	private String requestId;
	
	private String timestamp;
	
	private HiRequest hiRequest;

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

	public HiRequest getHiRequest() {
		return hiRequest;
	}

	public void setHiRequest(HiRequest hiRequest) {
		this.hiRequest = hiRequest;
	}
	

}
