package com.example.HAD.patientqueue.service;

import com.example.HAD.patientqueue.bean.DoctorId;
import com.example.HAD.patientqueue.bean.PatientListResponse;
import com.example.HAD.patientqueue.bean.PatientRequestBean;

public interface PatientQueueService {
	
	String addPatient(PatientRequestBean bean);
	
	String deletePatient(PatientRequestBean doctorId);
	 
	PatientListResponse getPatient(DoctorId doctorId);
	

}
