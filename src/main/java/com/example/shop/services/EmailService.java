package com.example.shop.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService{
	@Autowired 
	private JavaMailSender mailSender;
	@Async
	public void send(String to, String body) {
		try
		{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,"utf-8");
		messageHelper.setText(body, true);
		messageHelper.setTo(to);
		messageHelper.setSubject("confirm your email address");
		messageHelper.setFrom("hello@ecommerce.shop.com");
		mailSender.send(mimeMessage);
		}
		catch(MessagingException exception)
		{
			System.out.println("failed to send email:"+ exception.getMessage());
			throw new IllegalStateException("failed to send email");
		}
	}
	
	public String getBody(String confirmationLink)
	{
		StringBuffer body = new StringBuffer();
		try
		{
			File file = new File("confirmation-email.html");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null)
			{
				line = line.replace("${token}", confirmationLink);
				body.append(line);
			}
			reader.close();
			return body.toString();
		}
		catch(IOException ioException)
		{
			System.out.println("failed to read email file:"+ ioException.getMessage());
		}
		return null;
	}
	
}
