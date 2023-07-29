package com.example.HAD.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.HAD.patientregistration.bean.Employee;
import com.example.HAD.patientregistration.bean.TokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Utility {

	public String accessToken()
	{
	Employee emp=new Employee();
    Map<String, String> body= new HashMap<String, String>();

	      RestTemplate restTemplate = new RestTemplate();
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      headers.setAccept(Collections.singletonList(MediaType.ALL));
	      //headers.add("Authorization", "Bearer " + encodedAuth);
	      body.put("clientId", "SBX_002039");
	      body.put("clientSecret","216f7db8-6695-46d9-92b1-44f050dcd212");
	      String curr_body="";
		try {
			curr_body = new ObjectMapper().writeValueAsString(body);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      HttpEntity<String> httpEntity = new HttpEntity<>(curr_body, headers);
	
	ResponseEntity<TokenResponse> accessToken1=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/sessions", HttpMethod.POST, httpEntity, TokenResponse.class);
       return accessToken1.getBody().getAccessToken();
	}

}
