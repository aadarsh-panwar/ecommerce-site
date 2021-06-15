package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.util.JwtRequest;
import com.example.shop.models.util.JwtResponse;
import com.example.shop.services.AppUserService;
import com.example.shop.util.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
	@Autowired 
	private AppUserService appUserService;
	@Autowired 
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@RequestMapping(value = "customer", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) throws Exception {
		System.out.println("request:"+ request);
		try {
		UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
		this.authenticationManager.authenticate(authenticationToken);	
		}
		catch(UsernameNotFoundException usernameNotFound) {
			System.out.println(usernameNotFound.getMessage());
			throw new Exception("bad credentials");
		}
		catch(BadCredentialsException exception) {
			System.out.println(exception.getMessage());
			throw new Exception("bad credentials");
		}
		UserDetails appUser  = appUserService.loadUserByUsername(request.getUsername());
		String token = jwtUtil.generateToken(appUser);
		System.out.println("token created:" + token);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
