package com.example.HAD.login.dao;

import com.example.HAD.admin.doctor.docbean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.HAD.login.bean.LoginBean;

import java.util.List;

@Component

	
	public interface JpaRepo extends JpaRepository<LoginBean, String> {

		LoginBean findByIdAndPassword(String userName, String password);



	List<docbean> findByType(String doctor);
}

