package com.example.HAD;



import com.example.HAD.login.bean.LoginBean;
import com.example.HAD.login.dao.JpaRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.HAD.patientregistration.bean.Employee;
import com.example.HAD.patientregistration.bean.TokenResponse;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class HadApplication {
	String token="";
	
//	@Bean("Token")
//	public void SessionToken()
//	{
//		WebClient webClinet=WebClient.create();
//		Employee emp = new Employee();
//
//		Mono<TokenResponse> res = webClinet.post().uri("https://dev.abdm.gov.in/gateway/v0.5/sessions")
//				.body(Mono.just(emp), Employee.class).exchange()
//				.flatMap(clientResponse -> clientResponse.bodyToMono(TokenResponse.class));
//		 TokenResponse ew= res.block();
//		 token="Bearer "+ew.getAccessToken();
//		//return token;
//		
//	}
@Autowired
JpaRepo dao;


	@Bean
	InitializingBean LogingBean() {
		return () -> {

			dao.save(new LoginBean ("admin", "pass", "ROLE_admin"));

		};
	}
	@Bean
	//@DependsOn({"Token"})
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public WebClient getWebclient()
	{
		WebClient webClinet=WebClient.create();
		Employee emp = new Employee();
		emp.setClientId("SBX_002860");
		emp.setClientSecret("f7046678-9e0f-49ed-bbfd-220fb986c1ca");
		Mono<TokenResponse> res = webClinet.post().uri("https://dev.abdm.gov.in/gateway/v0.5/sessions")
				.body(Mono.just(emp), Employee.class).exchange()
				.flatMap(clientResponse -> clientResponse.bodyToMono(TokenResponse.class));
		 TokenResponse ew= res.block();
		 token="Bearer "+ew.getAccessToken();
		 System.out.println(token);
		String auth="Authorization";
		String id="X-CM-ID";
		String idValue="sbx";
		WebClient client = WebClient.builder()
				  .baseUrl("https://dev.abdm.gov.in/")
				  .defaultHeaders( httpHeaders -> {
					  httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			          httpHeaders.set(id, idValue);
			          httpHeaders.set(auth, token);})
				     .build();
		return client;
	}
	 @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	public static void main(String[] args) {
		SpringApplication.run(HadApplication.class, args);
	}






}
