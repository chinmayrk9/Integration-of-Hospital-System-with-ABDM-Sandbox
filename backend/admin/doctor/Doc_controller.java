package com.example.HAD.admin.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class Doc_controller {

    @Autowired
    doc_service service;



    @CrossOrigin(origins = "*")
    @PostMapping("/admin/savdoc")
    public ReturnId service(@RequestBody largeBean object){

        return service.savedoc(object);

    }

    @CrossOrigin (origins = "*")
    @PostMapping("/admin/deletedoc")
    public String delete(@RequestBody Delete_bean obj){

       return service.deletedoc(obj);

    }
    @CrossOrigin (origins = "*")
    @GetMapping("/receptionist/doclist")
    public List<docbean> getAllDoctorsWithType( ) {
        return service.getAllDoctorsWithType();
    }

}