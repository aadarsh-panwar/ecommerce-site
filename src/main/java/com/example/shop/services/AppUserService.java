package com.example.shop.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shop.models.token.ConfirmationToken;
import com.example.shop.repository.AppUserRepository;
import com.example.shop.security.PasswordEncoder;
import com.example.shop.user.AppUser;

@Service
public class AppUserService implements UserDetailsService{
	
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired 
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenConfirmationService tokenConfirmationService;
	@Autowired
	private EmailService emailService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findByUsernameIgnoreCase(username);
		if(appUser == null)
		{
			throw new UsernameNotFoundException("Username not found:"+ username);
		}
		return appUser;
	}
	
	public String signUpUser(AppUser appUser)
	{
		String username = appUser.getUsername();
		boolean usernameExists = appUserRepository.existsByUsernameIgnoreCase(username);
		if(usernameExists)
		{
			throw new IllegalStateException("email already taken:"+ username);
		}
		BCryptPasswordEncoder passwordEncoder = this.passwordEncoder.getPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(appUser.getPassword());
		appUser.setPassword(encodedPassword);
		appUserRepository.insert(appUser);
		// generate token and save it 
		// send email of confirmation with same token 
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
												LocalDateTime.now().plusMinutes(10),
												appUser.getId()
												);
		tokenConfirmationService.saveConfirmationToken(confirmationToken);
		String to = appUser.getUsername();
		String confirmationLink= "http://localhost:8080/shop/register/confirm-customer?token="
				 + token;
		String body = emailService.getBody(confirmationLink);	 
		emailService.send(to, body);
		return token;
		
	}
	
	
}
