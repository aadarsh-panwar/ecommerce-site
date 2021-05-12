package com.example.shop.models.token;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.shop.user.AppUser;

@Document("confirmation_token")
public class ConfirmationToken {
	@Id
	private String id;
	private String token;
	@Field("creation_time")
	private LocalDateTime creationTime;
	@Field("expiration_time")
	private LocalDateTime expirationTime;
	@Field("confirmation_time")
	private LocalDateTime confirmationTime;
	@Field("app_user_id")
	private String appUserId;
	
	
	public ConfirmationToken() {
		super();
	}
	public ConfirmationToken(String token, LocalDateTime creationTime, 
			LocalDateTime expirationTime, String appUserId) {
		
		this.token = token;
		this.creationTime = creationTime;
		this.expirationTime = expirationTime;
		this.appUserId =appUserId;
	}
	public ConfirmationToken(String token) {
		super();
		this.token = token;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	public LocalDateTime getExpirationTime() {
		return expirationTime;
	}
	public void setExpirationTime(LocalDateTime expirationTime) {
		this.expirationTime = expirationTime;
	}
	public LocalDateTime getConfirmationTime() {
		return confirmationTime;
	}
	public void setConfirmationTime(LocalDateTime confirmationTime) {
		this.confirmationTime = confirmationTime;
	}
	public String getAppUser() {
		return appUserId;
	}
	public void setAppUser(String appUserId) {
		this.appUserId = appUserId;
	}
	
	
}	
