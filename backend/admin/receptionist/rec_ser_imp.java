package com.example.HAD.admin.receptionist;

import com.example.HAD.login.bean.LoginBean;
import com.example.HAD.login.dao.JpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class rec_ser_imp implements rec_service {
    
    @Autowired
    rec_dao dao1;
    
    @Autowired
    JpaRepo dao2;

    @Autowired
    rec_dao.DoctorHospitalIdGenerator idgenerator;
    

    @Override
    public String saverec(largeBean obj) {
        
        recbean firstbean = new recbean();
        LoginBean secondbean = new LoginBean();

        String generated_id = idgenerator.generateDoctorHospitalId(obj.getName(), obj.getRole());
        String generated_hos_id = idgenerator.generateDoctorHospitalId(obj.getName(), "XYZ");
        
        firstbean.setMobile(obj.getMobile());
        firstbean.setName(obj.getName());
        firstbean.setAddress(obj.getAddress());
        firstbean.setHos_id(generated_hos_id);
        firstbean.setEmail_Id(obj.getEmail_Id());
        secondbean.setId(generated_id);
        secondbean.setPassword(obj.getPassword());
        secondbean.setType("ROLE_"+obj.getRole());
        firstbean.setLogin(secondbean);
        firstbean.setGender(obj.getGender());
        firstbean.setYearofBirth(obj.getYearofBirth());



        dao1.save(firstbean);

        dao2.save(secondbean);
        
        
        
        return "sucess";
    }

    @Override
    public String deleterec(Delete_rec_bean object) {


        dao2.deleteById(object.getUsername());
        return "deleted";
    }


}
