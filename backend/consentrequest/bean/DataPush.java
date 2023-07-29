package com.example.HAD.consentrequest.bean;

import java.util.List;

public class DataPush {
	
	private String pageNumber;
	private String pageCount;
	
	private String transactionId;
	private List<Entries> entries;
	
	private KeyMaterial keyMaterial;

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public List<Entries> getEntries() {
		return entries;
	}

	public void setEntries(List<Entries> entries) {
		this.entries = entries;
	}

	public KeyMaterial getKeyMaterial() {
		return keyMaterial;
	}

	public void setKeyMaterial(KeyMaterial keyMaterial) {
		this.keyMaterial = keyMaterial;
	}
	

}
