package com.example.HAD.carecontext.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.HAD.carecontext.bean.CareContextRequest;
import com.example.HAD.carecontext.bean.CareContextResponse;
import com.example.HAD.carecontext.bean.Link;
import com.example.HAD.patientregistration.bean.AbhaIDRequest;
import com.example.HAD.patientregistration.bean.Employee;
import com.example.HAD.patientregistration.bean.PatientDemographicBean;
import com.example.HAD.patientregistration.bean.TokenResponse;
import com.example.HAD.patientregistration.bean.TransactionId;
import com.example.HAD.patientregistration.dao.PatientDemographicRepository;
import com.example.HAD.patientregistration.dao.TransactionIdRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.*;
import reactor.core.publisher.Mono;

@RestController
public class CareContextController {
	@Autowired
	WebClient webclient;
	
	 @Autowired
	   RestTemplate restTemplate;
	
	
	@Autowired
	 PatientDemographicRepository patientDemographicRepository;

	@Autowired
	 TransactionIdRepository transactionIdRepository;
	WebClient client=WebClient.create();
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/doctor/add-context-status")
	public ResponseEntity<TransactionId> DbbackResponse(@RequestBody AbhaIDRequest object) {
		

	
		TransactionId bean=new TransactionId ();
		
		 bean= transactionIdRepository.findByAbhaId(object.getAbhaId());
		 
		 if(bean!=null)
		 {
		 return new ResponseEntity<TransactionId>(bean, HttpStatus.OK);
		 }
		 else 
			 return new ResponseEntity<TransactionId>(bean, HttpStatus.BAD_REQUEST);
			
		}
	
	
	@CrossOrigin(origins = "*")
	@PostMapping("/v0.5/links/link/on-add-contexts")
	public void CallbackResponse(@RequestBody CareContextResponse request) {
		

		TransactionId bean = transactionIdRepository.findByRequestId(request.getResp().getRequestId());
		bean.setStatus(request.getAcknowledgement().getStatus());
		transactionIdRepository.save(bean);
		
			
		}
//
//	@CrossOrigin(origins = "*")
//	@PostMapping("/doctor/care-context")
//	public Object getOtpRequest(@RequestBody CareContextRequest request) {
//		//System.out.print(request.getRequestId());
//		Employee emp=new Employee();
//		PatientDemographicBean bean=patientDemographicRepository.findByAbhaId(request.getId());
////
////		TokenResponse res = client.post().uri("https://dev.abdm.gov.in/gateway/v0.5/sessions")
////				.body(Mono.just(emp), Employee.class).exchange()
////				.flatMap(clientResponse -> clientResponse.bodyToMono(TokenResponse.class)).block();
//
//
//		//TokenResponse res 	 = restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/sessions", HttpMethod.GET, entity, String.class).getBody();
//		 Map<String, String> body= new HashMap<String, String>();
//
//		      RestTemplate restTemplate = new RestTemplate();
//		      HttpHeaders headers = new HttpHeaders();
//		      headers.setContentType(MediaType.APPLICATION_JSON);
//		      headers.setAccept(Collections.singletonList(MediaType.ALL));
//		      //headers.add("Authorization", "Bearer " + encodedAuth);
//		      body.put("clientId", "SBX_002039");
//		      body.put("clientSecret","216f7db8-6695-46d9-92b1-44f050dcd212");
//		      String curr_body="";
//			try {
//				curr_body = new ObjectMapper().writeValueAsString(body);
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		      HttpEntity<String> httpEntity = new HttpEntity<>(curr_body, headers);
//
//		ResponseEntity<TokenResponse> accessToken1=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/sessions", HttpMethod.POST, httpEntity, TokenResponse.class);
//	      String accessToken =accessToken1.getBody().getAccessToken();
//
//
//		Link link=request.getLink();
//		link.setAccessToken(bean.getAccessToken());
//		request.setLink(link);
//		UUID uuid = UUID.randomUUID();
//        String randomUUIDString = uuid.toString();
////Query query=new Query();
//        TimeZone timeZone=TimeZone.getTimeZone("UTC");
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
//        dateFormat.setTimeZone(timeZone);
//        String asISO= dateFormat.format(new Date());
//        request.setRequestId(randomUUIDString);
//        request.setTimestamp(asISO);
//
//        TransactionId transactionId = new TransactionId();
//		transactionId.setAbhaId(request.getId());
//		transactionId.setRequestId(randomUUIDString);
//		transactionIdRepository.save(transactionId);
//
//		String reqbody="";
//		try {
//			reqbody = new ObjectMapper().writeValueAsString(request);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.APPLICATION_JSON);
//        header.setAccept(Collections.singletonList(MediaType.ALL));
//        header.add("X-CM-ID","sbx");
//        header.add("Authorization", "Bearer " + accessToken);
//        HttpEntity<String> httpentity = new HttpEntity<>(reqbody, header);
//		 ResponseEntity<Object> objectResponseEntity=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/links/link/add-contexts", HttpMethod.POST, httpentity,Object.class);
//
////		  Mono<Object> response = webclient.post().uri(uriBuilder -> uriBuilder.path("gateway/v0.5/links/link/add-contexts").build())
////					.body(Mono.just(request), CareContextRequest.class).exchange()
////					.flatMap(clientResponse -> clientResponse.bodyToMono(Object.class));
//////
////String token ="Bearer"+res.getAccessToken();
////		Mono<Object> response = client.post().uri("https://dev.abdm.gov.in/gateway/v0.5/links/link/add-contexts")
////				.header("Authorization",token)
////				.header("X-CM-ID", "sbx")
////				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
////				.body(Mono.just(request), CareContextRequest.class).exchange()
////				.flatMap(clientResponse -> clientResponse.bodyToMono(Object.class));
//
//		return objectResponseEntity.getBody();
//	}

}
