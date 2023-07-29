package com.example.HAD.patientqueue.bean;

import java.util.List;

public class PatientListResponse {

	List<PatientRequestBean> patientList;

	public List<PatientRequestBean> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<PatientRequestBean> patientList) {
		this.patientList = patientList;
	}
}
