package com.example.HAD.patientregistration.bean;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class LoginModes {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	 private int id;
	private String requestId;
	
	
	private String mode;


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}

}
