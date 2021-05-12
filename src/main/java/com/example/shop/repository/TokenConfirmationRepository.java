package com.example.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.models.token.ConfirmationToken;

@Repository
public interface TokenConfirmationRepository extends MongoRepository<ConfirmationToken, String>{
	ConfirmationToken findByToken(String token);
}
