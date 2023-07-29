package com.example.HAD.admin.doctor;

import com.example.HAD.login.bean.LoginBean;
import com.example.HAD.login.dao.JpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class doc_ser_imp implements doc_service {
    
    @Autowired
    doc_dao dao1;
    
    @Autowired
    JpaRepo dao2;

    @Autowired
    doc_dao.DoctorHospitalIdGenerator idgenerator;
    

    @Override
    public ReturnId savedoc(largeBean obj) {
        
        docbean firstbean = new docbean();
        LoginBean secondbean = new LoginBean();
        ReturnId print = new ReturnId();



        String generated_id = idgenerator.generateDoctorHospitalId(obj.getName(), obj.getspeciality());
        String generated_hos_id = idgenerator.generateDoctorHospitalId(obj.getName(), "XYZ");
        
        firstbean.setMobile(obj.getMobile());
        firstbean.setName(obj.getName());
        firstbean.setAddress(obj.getAddress());
        firstbean.setHos_id(generated_hos_id);
        firstbean.setAbha_id(obj.getAbha_id());
        firstbean.setEmail_Id(obj.getEmail_Id());
        secondbean.setId(generated_id);
        secondbean.setPassword(obj.getPassword());
        secondbean.setType("ROLE_"+obj.getRole());
        firstbean.setLogin(secondbean);
        firstbean.setCountry(obj.getCountry());
        firstbean.setGender(obj.getGender());
        firstbean.setSpecility(obj.getspeciality());
        firstbean.setYearofBirth(obj.getYearofBirth());



        print.setId(generated_id);

        dao1.save(firstbean);

        dao2.save(secondbean);


        
        
        
        return print;
    }

    @Override
    public String deletedoc(Delete_bean obj) {

        dao2.deleteById(obj.getUserName());


       // docbean firs= dao1.findByLoginBean(bean);




        return "deleted";

   }



    @Override
    public List<docbean> getAllDoctorsWithType() {
        return dao1.findAll();
    }
}
