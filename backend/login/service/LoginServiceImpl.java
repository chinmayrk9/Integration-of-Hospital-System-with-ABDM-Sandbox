package com.example.HAD.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HAD.login.bean.LoginBean;
import com.example.HAD.login.dao.JpaRepo;

@Service
public class LoginServiceImpl {
	

	
	
	    @Autowired
	    JpaRepo dao;
	  
	    public String login(LoginBean bean) {


	        LoginBean credential =  dao.findByIdAndPassword(bean.getId(), bean.getPassword());

	        if(credential != null){
	            return credential.getType();
	        }
	        else{
	            return "invalid credentials";
	        }
	    }







	}

