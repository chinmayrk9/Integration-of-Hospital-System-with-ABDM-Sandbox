package com.example.HAD.MedicalRecord.services;

import com.example.HAD.MedicalRecord.bean.MedicalRecords;
import com.example.HAD.MedicalRecord.dao.savedata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class impl implements Save{

    @Autowired
    savedata saved;

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String str = formatter.format(date);
    @Override
public String savePres(MedicalRecords obj) {

//    MedicalRecords Bean = new MedicalRecords();
//
//    Bean.setPatientId(obj.getPatientId());
//    Bean.setPulse(obj.getPulse());
//    Bean.setBloodPressure(obj.getBloodPressure());
//    Bean.setDiagnosis(obj.getDiagnosis());
//    Bean.setConsentId("123");
//    Bean.setDosage(obj.getDosage());
//    Bean.setInstruction(obj.getInstruction());
//    Bean.setMedicine(obj.getMedicine());
//    Bean.setPattern(obj.getPattern());
//    Bean.setTimings(obj.getTimings());
//    Bean.setVistId("321");
//    Bean.setSymptoms(obj.getSymptoms());
//    Bean.setDate(str);
//
//    saved.save(Bean);
//
    return "sucess";
}

    @Override
    public List<MedicalRecords> findAllByPatientId(String object) {
        List<MedicalRecords> abcd = saved.findAllByPatientId(object);

        return abcd;
    }







//    Bean=

//    if(Bean!=null)
//    {
//        return new ResponseEntity<MedicalRecords>(Bean, HttpStatus.OK);
//    }
////    else
////        return new ResponseEntity<bean>(Bean, HttpStatus.BAD_REQUEST);
//
//
//    return null;





}
