package com.example.HAD.MedicalRecord.services;

import com.example.HAD.MedicalRecord.bean.MedicalRecords;

import java.util.List;

public interface Save {

    String  savePres(MedicalRecords obj);



    List<MedicalRecords> findAllByPatientId(String object);

}
