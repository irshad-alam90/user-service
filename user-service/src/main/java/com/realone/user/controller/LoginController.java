package com.realone.user.controller;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realone.security.model.JwtAuthenticationRequest;
import com.realone.security.model.JwtAuthenticationResponse;
import com.realone.security.model.JwtUserDetails;
import com.realone.security.service.JwtUserDetailsService;
import com.realone.security.util.JwtTokenUtil;

@RestController
@RequestMapping("user/auth")
public class LoginController {
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody JwtAuthenticationRequest request){
		this.doAuthenticate(request.getUserId(), request.getPassword());
		
		final JwtUserDetails jwtUserDetails =   (JwtUserDetails) userDetailsService.loadUserByUsername(request.getUserId());
		
		String token = this.jwtTokenUtil.generateToken(jwtUserDetails);
		//JwtAuthenticationResponse response = JwtAuthenticationResponse.bu
		return ResponseEntity.ok(new JwtAuthenticationResponse(token, jwtUserDetails.getUsername()));
	}

	private void doAuthenticate(String userId, String password) {
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, password);
		try {
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new RuntimeErrorException(null, "Invalid Username or Password !!");
		}
	}
}
