package com.example.HAD.consentrequest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.HAD.consentrequest.bean.ConsentMapping;
@Component

public interface ConsentRepository
    extends JpaRepository<ConsentMapping, Integer> {
	
	
	ConsentMapping findByConsentId(String requestId);
	
	
}

