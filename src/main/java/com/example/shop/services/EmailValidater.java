package com.example.shop.services;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.shop.repository.AppUserRepository;

@Component
public class EmailValidater implements Predicate<String>{
	@Override
	public boolean test(String username) {
		// check for valid email format
		String regex = "^(.+)@(.+)$";
		 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);
		if(!matcher.matches()) {
			throw new IllegalStateException("invalid email format");
		}
		return true;
	}
	
	
}
