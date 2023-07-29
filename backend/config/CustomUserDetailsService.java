package com.example.HAD.config;

import com.example.HAD.login.bean.LoginBean;
import com.example.HAD.login.dao.JpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService  {

	@Autowired
	JpaRepo repository;
	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  List<SimpleGrantedAuthority> roles=null;
	        LoginBean user = repository.findById(username).orElse(null);
	        if(user!=null)
	        {
	        	roles=Arrays.asList(new SimpleGrantedAuthority(user.getType()));
	        }
	        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), roles);
	    }
}
