package com.example.HAD.patientqueue.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.HAD.patientqueue.bean.DoctorId;
import com.example.HAD.patientqueue.bean.PatientListResponse;
import com.example.HAD.patientqueue.bean.PatientRequestBean;
import com.example.HAD.patientqueue.service.PatientQueueService;
@RestController
public class PatientQueue {
	
	@Autowired
	PatientQueueService patientQueueService;
	@CrossOrigin(origins = "*")
	@PostMapping("/receptionist/add-patient")
	public ResponseEntity<String> addPatient(@RequestBody PatientRequestBean object) {
		return  new ResponseEntity<String>(patientQueueService.addPatient(object),HttpStatus.OK);

	
	}
	 @CrossOrigin (origins = "*")
	@DeleteMapping("/doctor/delete-patient")
	
	public ResponseEntity<String> deletePatient(@RequestBody PatientRequestBean object) {
		return new ResponseEntity<String>(patientQueueService.deletePatient(object),HttpStatus.OK);

	
	}
	 @CrossOrigin (origins = "*")
	@PostMapping("/doctor/patient-list")
	public ResponseEntity<PatientListResponse> getPatient(@RequestBody DoctorId object) {
		return new ResponseEntity<PatientListResponse>(patientQueueService.getPatient(object),HttpStatus.OK);
	


}
}