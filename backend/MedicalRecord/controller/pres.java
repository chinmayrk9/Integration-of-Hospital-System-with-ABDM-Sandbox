package com.example.HAD.MedicalRecord.controller;

//import com.example.HAD.AbhaIDRequest;
//import com.example.HAD.PatientDemographicBean;
//import com.example.HAD.login.bean.loginbean;
import com.example.HAD.MedicalRecord.bean.MedicalRecords;
import com.example.HAD.MedicalRecord.bean.patientIDRes;
import com.example.HAD.MedicalRecord.services.Save;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
public class pres {

    @Autowired
    Save SavePres;



    @CrossOrigin (origins = "*")
    @PostMapping("/doctor/saveRecord")
    public String Savepres (@RequestBody MedicalRecords object) {

        return SavePres.savePres(object);

    }


    @CrossOrigin(origins = "*")
    @PostMapping("/doctor/getRecord")
    public ResponseEntity<List<MedicalRecords>> getdempgraphic(@RequestBody patientIDRes object) {

        List<MedicalRecords> patients = SavePres.findAllByPatientId(object.getPatientId());



        return ResponseEntity.ok(patients);
    }

}
