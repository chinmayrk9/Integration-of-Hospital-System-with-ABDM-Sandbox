package com.example.HAD.consentrequest.controller;

import ca.uhn.fhir.context.FhirContext;
import com.example.HAD.MedicalRecord.bean.MedicalRecords;
import com.example.HAD.MedicalRecord.dao.savedata;
import com.example.HAD.carecontext.bean.Resp;
import com.example.HAD.common.*;
import com.example.HAD.common.AbdmEncryption;
import com.example.HAD.common.DecryptionHandle;
import com.example.HAD.common.DecryptionRequest;
import com.example.HAD.common.DecryptionResponse;
import com.example.HAD.common.EncryptionRequest;
import com.example.HAD.common.EncryptionResponse;
import com.example.HAD.common.GenerateOpConsultOutput;
import com.example.HAD.common.KeysGenerator;
import com.example.HAD.common.Utility;
import com.example.HAD.config.ConsentResponse;
import com.example.HAD.consentrequest.bean.*;
import com.example.HAD.consentrequest.dao.ConsentRepository;
import com.example.HAD.consentrequest.dao.ConsentRequestRepository;
import com.example.HAD.patientregistration.bean.PatientDemographicBean;
import com.example.HAD.patientregistration.dao.PatientDemographicRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@RestController
public class ConsentController {
	
	@Autowired
	ConsentRepository consentRepository;
	
	@Autowired 
	PatientDemographicRepository patientDemographicRepository;
	 @Autowired
	   RestTemplate restTemplate;
	 
	 @Autowired
	 savedata medicalRecordRepository;
	 @Autowired
	 Utility utility;
	
	 @Autowired
	 GenerateOpConsultOutput generateOpConsultOutput;
	 
	 @Autowired
	 AbdmEncryption abdmEncryption;
	 
	 @Autowired
	 ConsentRequestRepository consentRequestRepository;
	
	 @Autowired
	 DecryptionHandle decryptionHandle;
	public String publicKey="";
	@Value("${HipId}")
	private String HipId;

	@Value("${HipName}")
	private String HipName;
	public String privateKey="";
	@CrossOrigin(origins = "*")
	@PostMapping("/doctor/init-consent")
	public Object careContext(@RequestBody ConsentInitRequest request) {
		//System.out.print(request.getRequestId());
		
	  	String accessToken=utility.accessToken();
		Hiu hiu=request.getConsent().getHiu();
		hiu.setId(HipId);
		Consent conset=request.getConsent();
		conset.setHiu(hiu);
		request.setConsent(conset);
	
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        TimeZone timeZone=TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
        dateFormat.setTimeZone(timeZone);
        String asISO= dateFormat.format(new Date());
        request.setRequestId(randomUUIDString);
        request.setTimestamp(asISO);
       
		String reqbody="";
		try {
			reqbody = new ObjectMapper().writeValueAsString(request);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.ALL));
        header.add("X-CM-ID","sbx");
        header.add("Authorization", "Bearer " + accessToken);
        HttpEntity<String> httpentity = new HttpEntity<>(reqbody, header);
		 ResponseEntity<Object> objectResponseEntity=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/consent-requests/init", HttpMethod.POST, httpentity,Object.class);
	      
		 DateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
	        dateFormat1.setTimeZone(timeZone);
		 ConsentRequest consentRequest=new ConsentRequest();
		 consentRequest.setAbhaId(request.getConsent().getPatient().getId());
		 consentRequest.setCreatedOn(asISO.substring(10));
		 consentRequest.setDoctorId(request.getConsent().getRequester().getIdentifier().getValue());
		 consentRequest.setPatientName(request.getConsent().getPatient().getPatientName());
		 consentRequest.setRequestStatus("Request Initialted");
		 consentRequest.setRequestId(randomUUIDString);
		 consentRequestRepository.save(consentRequest);

		return objectResponseEntity.getBody();
	}
	@CrossOrigin(origins = "*")
	@PostMapping("/v0.5/consent-requests/on-init")
	public void getreq(@RequestBody ConsentResponse object) {

		System.out.print(object.getRequestId());
		
		ConsentRequest consentRequest=	consentRequestRepository.findByRequestId(object.getResp().getRequestId());
	
		consentRequest.setConsentId(object.getConsentRequest().getId());
		 consentRequestRepository.save(consentRequest);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/v0.5/consents/hiu/notify")
	public void getreq(@RequestBody ConsentNotifyResponse object) {

		System.out.print(object.getRequestId());
		ConsentRequest bean = consentRequestRepository.findByConsentId(object.getNotification().getConsentRequestId());
		if(object.getNotification().getStatus().equalsIgnoreCase("Granted"))
		{
			DateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
		   TimeZone timeZone=TimeZone.getTimeZone("UTC");
            dateFormat1.setTimeZone(timeZone);
			String asISO=dateFormat1.format(new Date());

			bean.setGrantedOn(asISO.substring(10));
			//bean.setExpiryOn(object.getNotification().get)
			bean.setRequestStatus("Consent Granted");
			
		}
		else 
		{
			bean.setGrantedOn("-");
			//bean.setExpiryOn(object.getNotification().get)
			bean.setRequestStatus("Consent Denied");
			
		}
		bean.setConsentId(object.getNotification().getConsentArtefacts().get(0).getId());
		consentRequestRepository.save(bean);
		List<ConsentArtefacts> consentArt=object.getNotification().getConsentArtefacts();
		String consentArtString="";
		String accessToken=utility.accessToken();
		 HttpHeaders header = new HttpHeaders();
	        header.setContentType(MediaType.APPLICATION_JSON);
	        header.setAccept(Collections.singletonList(MediaType.ALL));
	        header.add("X-CM-ID","sbx");
	        header.add("Authorization", "Bearer " + accessToken);
	        
	        HipOnNotify hipOnNotify =new HipOnNotify();
			Acknowledgement ack=new Acknowledgement();
			Resp resp=new Resp();
			UUID uuid = UUID.randomUUID();
	        String randomUUIDString = uuid.toString();
	        TimeZone timeZone=TimeZone.getTimeZone("UTC");
	        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
	        dateFormat.setTimeZone(timeZone);
	        String asISO= dateFormat.format(new Date());
	        ack.setStatus("OK");
	        ack.setConsentId(object.getNotification().getConsentArtefacts().get(0).getId());
	        resp.setRequestId(object.getRequestId());
	        hipOnNotify.setRequestId(randomUUIDString);
	        hipOnNotify.setTimestamp(asISO);
	        hipOnNotify.setAcknowledgement(ack);
	        hipOnNotify.setResp(resp);
	      String reqbody="";
			try {
				reqbody = new ObjectMapper().writeValueAsString(hipOnNotify);
			} catch (JsonProcessingException e) {
				
				e.printStackTrace();
			}
	  
	        HttpEntity<String> httpentity = new HttpEntity<>(reqbody, header);
			 ResponseEntity<Object> objectResponseEntity=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/consents/hiu/on-notify", HttpMethod.POST, httpentity,Object.class);
		      

		for(int i=0;i<consentArt.size();i++)
		{
			consentArtString.concat(consentArt.get(i).getId());
			if(i!=(consentArt.size()-1))
			{		
			consentArtString.concat("^");
			}
			
			 UUID uuid1 = UUID.randomUUID();
	        String randomUUIDString1 = uuid.toString();
	         TimeZone timeZone1=TimeZone.getTimeZone("UTC");
	        DateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
	        dateFormat1.setTimeZone(timeZone1);
	         String asISO1= dateFormat.format(new Date());
			FetchConsent fetchConsent=new FetchConsent();
			fetchConsent.setRequestId(randomUUIDString1);
			fetchConsent.setTimestamp(asISO1);
			fetchConsent.setConsentId(consentArt.get(i).getId());
			 String reqbody1="";
				try {
					reqbody1 = new ObjectMapper().writeValueAsString(fetchConsent);
				} catch (JsonProcessingException e) {
					
					e.printStackTrace();
				}
				String accessToken1=utility.accessToken();
				 HttpHeaders header1 = new HttpHeaders();
			        header1.setContentType(MediaType.APPLICATION_JSON);
			        header1.setAccept(Collections.singletonList(MediaType.ALL));
			        header1.add("X-CM-ID","sbx");
			        header1.add("Authorization", "Bearer " + accessToken1);
		        HttpEntity<String> httpentity1 = new HttpEntity<>(reqbody1, header1);
				 ResponseEntity<Object> objectResponseEntity1=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/consents/fetch", HttpMethod.POST, httpentity1,Object.class);
			      
		}
//		bean.setConsentArtifacts(consentArtString);
//		consentRepository.save(bean);
		
		//
	
	
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/v0.5/consents/hip/notify")
	public void gethipnotify(@RequestBody HipNotify object) {
		
		
		String accessToken=utility.accessToken();
	      HipOnNotify hipOnNotify =new HipOnNotify();
			Acknowledgement ack=new Acknowledgement();
			Resp resp=new Resp();
			UUID uuid = UUID.randomUUID();
	        String randomUUIDString = uuid.toString();
	        TimeZone timeZone=TimeZone.getTimeZone("UTC");
	        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
	        dateFormat.setTimeZone(timeZone);
	        String asISO= dateFormat.format(new Date());
	        ack.setStatus("OK");
	        ack.setConsentId(object.getNotification().getConsentDetail().getConsentId());
	        resp.setRequestId(object.getRequestId());
	        hipOnNotify.setRequestId(randomUUIDString);
	        hipOnNotify.setTimestamp(asISO);
	        hipOnNotify.setAcknowledgement(ack);
	        hipOnNotify.setResp(resp);
	      String reqbody="";
			try {
				reqbody = new ObjectMapper().writeValueAsString(hipOnNotify);
			} catch (JsonProcessingException e) {
		
				e.printStackTrace();
			}
	      HttpHeaders header = new HttpHeaders();
	        header.setContentType(MediaType.APPLICATION_JSON);
	        header.setAccept(Collections.singletonList(MediaType.ALL));
	        header.add("X-CM-ID","sbx");
	        header.add("Authorization", "Bearer " + accessToken);
	        HttpEntity<String> httpentity = new HttpEntity<>(reqbody, header);
			 ResponseEntity<Object> objectResponseEntity=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/consents/hip/on-notify", HttpMethod.POST, httpentity,Object.class);
		      
			 ConsentMapping mapping=new ConsentMapping();
			 mapping.setAbhaId(object.getNotification().getConsentDetail().getPatient().getId());
			 mapping.setConsentId(object.getNotification().getConsentDetail().getConsentId());
			 mapping.setFromDate(object.getNotification().getConsentDetail().getPermission().getDateRange().getFrom());
			 mapping.setToDate(object.getNotification().getConsentDetail().getPermission().getDateRange().getTo());
			 
			 consentRepository.save(mapping);
			 

	//	
	
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/v0.5/consents/on-fetch")
	public void onFetch(@RequestBody OnFetchResponse object) throws Exception {
		
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        TimeZone timeZone=TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
        dateFormat.setTimeZone(timeZone);
        String asISO= dateFormat.format(new Date());
		CmRequest cmrequest=new CmRequest();
		HiRequest hirequest=new HiRequest();
		ConsentId consent=new ConsentId();
		hirequest.setConsent(consent);
		KeyMaterial keyMaterial=new KeyMaterial();
		DhPublicKey dhPublicKey=new DhPublicKey();
		hirequest.setDateRange(object.getConsent().getConsentDetail().getPermission().getDateRange());
		consent.setId(object.getConsent().getConsentDetail().getConsentId());
		KeysGenerator keysGenerator=new KeysGenerator();
		com.example.HAD.common.KeyDetails receiver=keysGenerator.generate();
		
		keyMaterial.setCryptoAlg("ECDH");
		keyMaterial.setCurve("Curve25519");
		keyMaterial.setNonce(receiver.getNonce());
		publicKey=receiver.getNonce();
		privateKey=receiver.getPrivateKey();
		dhPublicKey.setKeyValue(receiver.getPublicKey());
		dhPublicKey.setParameters("Curve25519/32byte random key");
		dhPublicKey.setExpiry("2023-12-26T10:25:26.274Z");
		
		keyMaterial.setDhPublicKey(dhPublicKey);
		hirequest.setKeyMaterial(keyMaterial);
		hirequest.setDataPushUrl("https://3e6b-119-161-98-68.in.ngrok.io/login/hiu-receive-data");
		
		cmrequest.setHiRequest(hirequest);
		cmrequest.setRequestId(randomUUIDString);
		cmrequest.setTimestamp(asISO);
		
		ConsentRequest consentRequest=	consentRequestRepository.findByConsentId(object.getConsent().getConsentDetail().getConsentId());
		consentRequest.setRequestId(randomUUIDString);
		
		consentRequestRepository.save(consentRequest);
		
		String accessToken=utility.accessToken();
	
		   String reqbody="";
					try {
						reqbody = new ObjectMapper().writeValueAsString(cmrequest);
					} catch (JsonProcessingException e) {
				
						e.printStackTrace();
					}
			      HttpHeaders header = new HttpHeaders();
			        header.setContentType(MediaType.APPLICATION_JSON);
			        header.setAccept(Collections.singletonList(MediaType.ALL));
			        header.add("X-CM-ID","sbx");
			        header.add("Authorization", "Bearer " + accessToken);
			        HttpEntity<String> httpentity = new HttpEntity<>(reqbody, header);
					 ResponseEntity<Object> objectResponseEntity=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/health-information/cm/request", HttpMethod.POST, httpentity,Object.class);
				      

	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/v0.5/health-information/hiu/on-request")
	public void onRequest(@RequestBody HipOnRequest object) throws Exception {
		
		ConsentRequest consentRequest=	consentRequestRepository.findByRequestId(object.getResp().getRequestId());
		consentRequest.setTransactionId(object.getHiRequest().getTransactionId());
		consentRequestRepository.save(consentRequest);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/v0.5/health-information/hip/request")
	public void hipRequest(@RequestBody CmRequest object) throws Exception {
		
		
		UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        TimeZone timeZone=TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSSSSS");
        dateFormat.setTimeZone(timeZone);
        String asISO= dateFormat.format(new Date());
        HipOnRequest hipOnRequest=new HipOnRequest();
		String accessToken=utility.accessToken();
		HnRequest hiOnRequest=new HnRequest();
		hipOnRequest.setRequestId(randomUUIDString);
		hipOnRequest.setTimestamp(asISO);
		hiOnRequest.setTransactionId(object.getTransactionId());
		hiOnRequest.setSessionStatus("ACKNOWLEDGED");
		hipOnRequest.setHiRequest(hiOnRequest);
		Resp resp=new Resp();
		resp.setRequestId(object.getRequestId());
		hipOnRequest.setResp(resp);
		
		 String reqbody1="";
			try {
				reqbody1 = new ObjectMapper().writeValueAsString(hipOnRequest);
			} catch (JsonProcessingException e) {
			
				e.printStackTrace();
			}
	      HttpHeaders header1 = new HttpHeaders();
	        header1.setContentType(MediaType.APPLICATION_JSON);
	        header1.setAccept(Collections.singletonList(MediaType.ALL));
	        header1.add("X-CM-ID","sbx");
	        header1.add("Authorization", "Bearer " + accessToken);
	        HttpEntity<String> httpentity1 = new HttpEntity<>(reqbody1, header1);
			 ResponseEntity<Object> objectResponseEntity=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/health-information/hip/on-request", HttpMethod.POST, httpentity1,Object.class);
		      
		
		
		
		
		
		
		
		
		
		
		
		
		 ConsentMapping mapping= consentRepository.findByConsentId(object.getHiRequest().getConsent().getId());
		 
	 PatientDemographicBean demographicData= patientDemographicRepository.findByAbhaId(mapping.getAbhaId());
//		 MedicalRecords MedicalRecords=new MedicalRecords();
//		 MedicalRecords.setDate("2022-8-14");
//		 MedicalRecords.setDoctorId("1");
//		 MedicalRecords.setDosage("3 Times");
//		 MedicalRecords.setInstruction("No smoking");
//		 MedicalRecords.setMedicine("zady");
//		 MedicalRecords.setPatientId("12");
//		 MedicalRecords.setPattern("Cold");
//		 MedicalRecords.setSymptoms("Fever");
//		 MedicalRecords.setTimings("1-1-1");
//		 MedicalRecords.setVisitId("2123");
		List<MedicalRecords> medicalRecords=medicalRecordRepository.findByPatientId(demographicData.getPatientId());
		//medicalRecords.add(MedicalRecords);
		List<MedicalRecords> dataToSend=new ArrayList<>();
		
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");      
	      String dateStr1 =object.getHiRequest().getDateRange().getFrom().substring(0,10);
	      String dateStr2 = object.getHiRequest().getDateRange().getTo().substring(0,10);
	      //Parsing the given String to Date object
	      Date date1 = formatter.parse(dateStr1);  
	      Date date2 = formatter.parse(dateStr2); 
	    
	      Boolean bool1 = date1.after(date2);  
	      Boolean bool2 = date1.before(date2);
	      Boolean bool3 = date1.equals(date2);
	      DataPush datapush=new DataPush();
	      datapush.setPageCount("1");
	      datapush.setPageNumber("1");
	      datapush.setTransactionId(object.getTransactionId());
		for(int i=0;i<medicalRecords.size();i++)
		{
			String dateStr3=medicalRecords.get(i).getDate();
			  Date date3=formatter.parse(dateStr3); 
			  if( date3.after(date1) && date3.before(date2))
			  {
				  
				  dataToSend.add(medicalRecords.get(i));
			  }
			
		}
		
		KeysGenerator keysGenerator=new KeysGenerator();
		com.example.HAD.common.KeyDetails receiver=keysGenerator.generate();
//		KeysGenerator keysGenerator1=new KeysGenerator();
//		com.example.HAD.common.KeyDetails sender=keysGenerator1.generate();
		List<Entries> entries=new ArrayList();
		String publ="";
		
		DataPushNotification DataPushNotification=new DataPushNotification();
		DataTransferNotification DataTransferNotification=new DataTransferNotification();
		
		
		Notifier Notifier=new Notifier();
		Notifier.setId("");
		Notifier.setType("HIP");
		StatusNotification StatusNotification=new StatusNotification();
		 List<StatusResponses> statusres =new ArrayList<>();
		for(int i=0;i<dataToSend.size();i++)
		{
			StatusResponses StatusResponses=new StatusResponses();
			org.hl7.fhir.r4.model.Bundle req = new org.hl7.fhir.r4.model.Bundle();
			MedicalRecords ptnrecord=dataToSend.get(i);
			org.hl7.fhir.r4.model.Bundle rec=generateOpConsultOutput.populateOPConsultNoteBundle(ptnrecord,req);
			Entries entry=new Entries();
			FhirContext ct=FhirContext.forR4();
			ca.uhn.fhir.parser.IParser parser;
			parser=ct.newJsonParser();
			parser.setPrettyPrint(true);
			
			String encBundle=parser.encodeResourceToString(rec);
			
			EncryptionRequest request=new EncryptionRequest();
			request.setPlainTextData(encBundle);
			request.setSenderNonce(receiver.getNonce());
			request.setSenderPrivateKey(receiver.getPrivateKey());
			request.setSenderPublicKey(receiver.getPublicKey());
			request.setReceiverNonce(object.getHiRequest().getKeyMaterial().getNonce());//);
			request.setReceiverPublicKey(object.getHiRequest().getKeyMaterial().getDhPublicKey().getKeyValue());//.getHiRequest().getKeyMaterial().getDhPublicKey().getKeyValue());
			System.out.println(encBundle);
			EncryptionResponse response=abdmEncryption.encrypt(request);
			 publ=response.getKeyToShare();
			entry.setContent(response.getEncryptedData());
			entry.setMedia("application/fhir+json");
			entry.setChecksum("121");
			entry.setCareContextReference(ptnrecord.getVisitId());

			entries.add(entry);
			
			StatusResponses.setCareContextReference(ptnrecord.getVisitId());
			StatusResponses.setDescription("");
			StatusResponses.setHiStatus("OK");
			
			statusres.add(StatusResponses);
			
			
			
		}
		StatusNotification.setHipId("");
		StatusNotification.setSessionStatus("TRANSFERRED");
		StatusNotification.setStatusResponses(statusres);
		
		DataTransferNotification.setConsentId(object.getHiRequest().getConsent().getId());
		DataTransferNotification.setTransactionId(object.getTransactionId());
		DataTransferNotification.setNotifier(Notifier);
		DataTransferNotification.setStatusNotification(StatusNotification);
		DataTransferNotification.setDoneAt(asISO);
		
		
		
		 uuid = UUID.randomUUID();
         randomUUIDString = uuid.toString();
         
         DataPushNotification.setRequestId(randomUUIDString);
         DataPushNotification.setTimestamp(asISO);
         DataPushNotification.setNotification(DataTransferNotification);
         
		
		
		
		KeyMaterial keyMaterial=new KeyMaterial();
		keyMaterial.setCryptoAlg("ECDH");
		keyMaterial.setCurve("Curve25519");
		keyMaterial.setNonce(receiver.getNonce());
		DhPublicKey dhPublicKey=new DhPublicKey();
		dhPublicKey.setExpiry(object.getHiRequest().getKeyMaterial().getDhPublicKey().getExpiry());
		dhPublicKey.setKeyValue(publ);
		dhPublicKey.setParameters("Curve25519/32byte random key");
		keyMaterial.setDhPublicKey(dhPublicKey);
		datapush.setKeyMaterial(keyMaterial);
		datapush.setEntries(entries);
		datapush.setTransactionId(object.getTransactionId());
		 String reqbody="";
			try {
				reqbody = new ObjectMapper().writeValueAsString(datapush);
			} catch (JsonProcessingException e) {
			
				e.printStackTrace();
			}
	      HttpHeaders header = new HttpHeaders();
	        header.setContentType(MediaType.APPLICATION_JSON);
	        header.setAccept(Collections.singletonList(MediaType.ALL));
	   
	        HttpEntity<String> httpentity = new HttpEntity<>(reqbody, header);
			 ResponseEntity<String> objectResponseEntity1=restTemplate.exchange(object.getHiRequest().getDataPushUrl(), HttpMethod.POST, httpentity,String.class);
		    System.out.print("K:");
		    
		    
		    String req="";
			try {
				req = new ObjectMapper().writeValueAsString(DataPushNotification);
			} catch (JsonProcessingException e) {
			
				e.printStackTrace();
			}
	      HttpHeaders header5 = new HttpHeaders();
	        header5.setContentType(MediaType.APPLICATION_JSON);
	        header5.setAccept(Collections.singletonList(MediaType.ALL));
	   
	        HttpEntity<String> httpentity5 = new HttpEntity<>(req, header5);
			 ResponseEntity<String> objectResponseEntity5=restTemplate.exchange("https://dev.abdm.gov.in/gateway/v0.5/health-information/notify", HttpMethod.POST, httpentity5,String.class);
		
		    
		    
		    
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/login/hiu-receive-data")
	public void hiuRequest(@RequestBody DataPush object) throws Exception {
		
		
		for(int z=0;z<object.getEntries().size();z++)
		{
		DecryptionRequest decryptionRequest=new DecryptionRequest(privateKey,publicKey,
				object.getKeyMaterial().getDhPublicKey().getKeyValue(),object.getKeyMaterial().getNonce(),object.getEntries().get(z).getContent());
		
		DecryptionResponse response=decryptionHandle.decrypt(decryptionRequest);
		
		ConsentRequest consentRequest=	consentRequestRepository.findByTransactionId(object.getTransactionId());
		PatientDemographicBean demographicData= patientDemographicRepository.findByAbhaId(consentRequest.getAbhaId());

		String consentId=consentRequest.getConsentId();
		System.out.println(response.getDecryptedData());
		 JSONObject jsonObject = new JSONObject(response.getDecryptedData()); 
		 JSONArray  obj= (JSONArray) jsonObject.get("entry");
		 JSONObject a=(JSONObject)obj.get(5);
		 MedicalRecords records=new MedicalRecords();
		 JSONObject b=(JSONObject) a.get("resource");
		 JSONObject c=(JSONObject) b.get("medicationCodeableConcept");
		 JSONArray d=(JSONArray) b.get("dosageInstruction");
		 JSONObject  m= (JSONObject) d.get(0);
		 JSONArray  e= (JSONArray) m.get("additionalInstruction");
		 JSONObject f=(JSONObject)e.get(0);
		 
		 JSONObject g=(JSONObject)m.get("route");
		 JSONObject h=(JSONObject)obj.get(4);
		 JSONObject i=(JSONObject)h.get("resource");
		 JSONObject n=(JSONObject)i.get("code");
		 JSONObject j=(JSONObject)obj.get(6);
		 JSONObject k=(JSONObject)j.get("resource");
		 JSONObject l=(JSONObject)k.get("code");
		 records.setPattern(m.getString("text"));
		 records.setMedicine((String) c.get("text"));
		 records.setConsentId(consentId);
		 records.setTimings(f.getString("text"));
		 records.setInstruction(g.getString("text"));
		 records.setSymptoms(n.getString("text"));
		 records.setDosage(l.getString("text"));

			records.setDoctorId(consentRequest.getDoctorId());
			records.setPatientId(demographicData.getPatientId());
		 	medicalRecordRepository.save(records);
		}
		System.out.println("q:");
	}
	
	
	
	
	}

	
	


