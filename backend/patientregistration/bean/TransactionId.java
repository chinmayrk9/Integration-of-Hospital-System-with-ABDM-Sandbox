package com.example.HAD.patientregistration.bean;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class TransactionId {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	private String requestId;
	
	
	private String transactionId;
	
	private String status;
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	private String abhaId;


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getAbhaId() {
		return abhaId;
	}


	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}



}
