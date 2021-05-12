package com.example.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.user.AppUser;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String>{
	AppUser findByUsernameIgnoreCase(String username);
	boolean existsByUsernameIgnoreCase(String username);
}
