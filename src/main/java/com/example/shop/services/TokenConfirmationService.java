package com.example.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.models.token.ConfirmationToken;
import com.example.shop.repository.TokenConfirmationRepository;

@Service
public class TokenConfirmationService {
	@Autowired
	private TokenConfirmationRepository tokenConfirmationRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		this.tokenConfirmationRepository.save(token);
	}
	public ConfirmationToken getByToken(String token) {
		return tokenConfirmationRepository.findByToken(token);
	}
}
