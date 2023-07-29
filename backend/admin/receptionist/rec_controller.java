package com.example.HAD.admin.receptionist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class rec_controller {

    @Autowired
    rec_service service;



    @CrossOrigin(origins = "*")
    @PostMapping("/admin/savrec")
    public String service(@RequestBody largeBean object){

        return service.saverec(object);

    }

    @CrossOrigin (origins = "*")
    @PostMapping("/admin/deleterec")
    public String delete(@RequestBody Delete_rec_bean obj){

       return service.deleterec(obj);

    }

}
