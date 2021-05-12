package com.example.shop.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
	
  public BCryptPasswordEncoder getPasswordEncoder()
  {
	  return new BCryptPasswordEncoder();
  }
}
