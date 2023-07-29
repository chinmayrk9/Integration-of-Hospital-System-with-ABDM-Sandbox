package com.example.HAD.patientregistration.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.HAD.patientregistration.bean.TransactionId;
@Component

public interface TransactionIdRepository
    extends JpaRepository<TransactionId, Integer> {
	
	TransactionId findByAbhaId(String requestId);

	TransactionId findByRequestId(String object);
	
	
}

