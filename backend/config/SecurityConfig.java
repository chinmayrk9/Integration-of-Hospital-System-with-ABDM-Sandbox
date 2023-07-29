package com.example.HAD.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter   {

	 @Autowired
	    private CustomUserDetailsService userDetailsService;

	    @Autowired
	    private JwtFilter jwtFilter;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	    }
	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return NoOpPasswordEncoder.getInstance();
	    }
	    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Override
	    public void configure(HttpSecurity  http) throws Exception {
//	        http.csrf().disable().authorizeRequests().antMatchers("/**")
//	                .permitAll().anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
//	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
//	    //	web.ignoring().mvcMatchers("v0.5/users/auth/on-fetch-modes", "/v0.5/login");

			http.cors();
			http.csrf()
					.disable()
					.authorizeRequests()
					.antMatchers("/login/**")
					.permitAll()
					.antMatchers("/receptionist/**")
					.hasRole("receptionist")
					.antMatchers("/doctor/**")
					.hasRole("doctor")
					.antMatchers("/admin/**")
					.hasRole("admin")
					.anyRequest()
					.authenticated()
					.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	    }
	 
	}
