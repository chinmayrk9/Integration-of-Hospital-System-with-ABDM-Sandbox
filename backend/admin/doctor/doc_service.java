package com.example.HAD.admin.doctor;

import java.util.List;

public interface doc_service {

    ReturnId savedoc(largeBean obj);

    String deletedoc(Delete_bean object);

//     List<docbean> getDoctor();


    List<docbean> getAllDoctorsWithType();
}
