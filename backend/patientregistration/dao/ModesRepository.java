package com.example.HAD.patientregistration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.HAD.patientregistration.bean.LoginModes;
@Component

public interface ModesRepository
    extends JpaRepository<LoginModes, Integer> {
	
	 LoginModes findByRequestId(String requestId);
	
	
}

