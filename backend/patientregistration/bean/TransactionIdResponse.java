package com.example.HAD.patientregistration.bean;

import java.util.List;

public class TransactionIdResponse {
	
private String requestId;

private String timestamp;


private AuthTransactionId auth;

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

public AuthTransactionId getAuth() {
	return auth;
}

public void setAuth(AuthTransactionId auth) {
	this.auth = auth;
}

public Resp getResp() {
	return resp;
}

public void setResp(Resp resp) {
	this.resp = resp;
}

	

}
