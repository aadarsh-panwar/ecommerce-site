package com.example.shop.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.shop.services.AppUserService;
import com.example.shop.util.JwtUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AppUserService appUserService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String tokenHeader = request.getHeader("Authorization");
		String jwtToken = null;
		String username = null;
		if(tokenHeader != null && tokenHeader.startsWith("Bearer"))
		{
			jwtToken = tokenHeader.substring(7);
			try
			{
			username = jwtUtil.extractUsername(jwtToken);
			}
			catch(Exception exception)
			{
			System.out.println("exception in extracting username:"+ exception.getMessage());
			throw exception;
			}
			UserDetails userDetails = this.appUserService.loadUserByUsername(username);
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
			{
				 if (jwtUtil.validateToken(jwtToken, userDetails)) {
		                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                SecurityContextHolder.getContext().setAuthentication(authentication); 
		            }
			}
			else
			{
				// do nothing for now
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
