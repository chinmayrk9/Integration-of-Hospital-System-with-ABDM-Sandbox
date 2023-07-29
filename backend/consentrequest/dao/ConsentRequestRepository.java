package com.example.HAD.consentrequest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.HAD.consentrequest.bean.ConsentRequest;
@Component

public interface ConsentRequestRepository
    extends JpaRepository<ConsentRequest, Integer> {
	
	
	List<ConsentRequest> findAllByAbhaId(String requestId);
	
	ConsentRequest findByRequestId(String id);

	ConsentRequest findByConsentId(String consentRequestId);
	
	ConsentRequest findByAbhaIdAndCreatedOn(String consentRequestId,String createdOn);
	
	ConsentRequest findByAbhaId(String consentRequestId);
	
	List<ConsentRequest> findAllByDoctorId(String requestId);
	
	
	ConsentRequest findByTransactionId(String transactionId);
	
	
	
	
}

