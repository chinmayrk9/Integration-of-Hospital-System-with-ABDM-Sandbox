package com.example.HAD.config;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService service;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String hipId=httpServletRequest.getHeader("X-Hip-Id");
        String hiuId=httpServletRequest.getHeader("X-Hiu-Id");

        String token = null;
        String userName = null;
        String payload;
        if(hipId!=null || hiuId != null)
        {
            token = authorizationHeader.substring(7);
        	String[] chunks = token.split("\\.");
        	Base64.Decoder decoder = Base64.getUrlDecoder();

        	String header = new String(decoder.decode(chunks[0]));
        	 payload = new String(decoder.decode(chunks[1]));
        	
        	 
        	 String b64payload = chunks[1];
        	 String jsonString = new String(decoder.decode(b64payload), "UTF-8");
        	 JSONObject jsonObject = new JSONObject(payload);  
        	 String a=(String)jsonObject.get("clientId");
        	 if(a.equalsIgnoreCase("gateway"))
        	 {
        		 UserDetails userDetails=  new org.springframework.security.core.userdetails.User("gateway", "123", Arrays.asList(new SimpleGrantedAuthority("doctor")));
        		 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                         new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                 usernamePasswordAuthenticationToken
                         .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        		 
        	 }
        	
        }
        if (hipId==null && hiuId == null && authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

