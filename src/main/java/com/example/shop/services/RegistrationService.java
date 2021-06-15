package com.example.shop.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.models.request.RegistrationRequest;
import com.example.shop.models.token.ConfirmationToken;
import com.example.shop.repository.AppUserRepository;
import com.example.shop.user.AppUser;
import com.example.shop.user.AppUserRole;
@Service
public class RegistrationService {
	@Autowired
	private EmailValidater emailValidator;
	@Autowired 
	private AppUserService appUserService;
	@Autowired 
	private TokenConfirmationService tokenConfirmationService;
	@Autowired
	private AppUserRepository appUserRepository;
	
	public String register(RegistrationRequest request) throws IllegalStateException {
		System.out.println("request:"+ request);
		String username = request.getUsername();
		boolean result= emailValidator.test(username);
		AppUser appUser = new AppUser();
		appUser.setId(UUID.randomUUID().toString());
		appUser.setUsername(request.getUsername());
		appUser.setPassword(request.getPassword());
		appUser.setAppUserRole(AppUserRole.CUSTOMER);
		return appUserService.signUpUser(appUser);
		
	}
	public String confirm(String token) {
		ConfirmationToken confirmationToken = tokenConfirmationService.getByToken(token);
		if(confirmationToken == null) {
			throw new IllegalStateException("invalid token");
		}
		String appUserId= confirmationToken.getAppUser();
		Optional<AppUser> appUserWrapper = appUserRepository.findById(appUserId);
		if(appUserWrapper.isEmpty()) {
			throw new IllegalStateException("user does not exists");
		}
		AppUser appUser = appUserWrapper.get();
		if(confirmationToken.getConfirmationTime() != null) {
			throw new IllegalStateException("email already confirmed");
		}
		LocalDateTime expirationTime= confirmationToken.getExpirationTime();
		if(expirationTime.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}
		confirmationToken.setConfirmationTime(LocalDateTime.now());
		tokenConfirmationService.saveConfirmationToken(confirmationToken);
		appUser.setEnabled(true);
		appUserRepository.save(appUser);
		return "confirmed";
	}
}
