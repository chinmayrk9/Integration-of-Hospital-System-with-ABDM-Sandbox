package com.example.HAD.consentrequest.bean;

public class Permission {
	private String accessMode;
	
	private String dataEraseAt;
	
	private Frequency frequency;
	
	private DateRange dateRange;

	public String getAccessMode() {
		return accessMode;
	}

	public void setAccessMode(String accessMode) {
		this.accessMode = accessMode;
	}

	public String getDataEraseAt() {
		return dataEraseAt;
	}

	public void setDataEraseAt(String dataEraseAt) {
		this.dataEraseAt = dataEraseAt;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public DateRange getDateRange() {
		return dateRange;
	}

	public void setDateRange(DateRange dateRange) {
		this.dateRange = dateRange;
	}

}
