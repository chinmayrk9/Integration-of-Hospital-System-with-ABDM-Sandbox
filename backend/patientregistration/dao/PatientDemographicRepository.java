package com.example.HAD.patientregistration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.HAD.patientregistration.bean.PatientDemographicBean;
@Component

public interface PatientDemographicRepository
    extends JpaRepository<PatientDemographicBean, Integer> {
	
	PatientDemographicBean findByAbhaId(String requestId);
	
	PatientDemographicBean findByAbhaIdOrMobileNumber(String abhaId,String mobileNumber);

//	PatientDemographicBean findByRequestId(String object);
	
	
}

