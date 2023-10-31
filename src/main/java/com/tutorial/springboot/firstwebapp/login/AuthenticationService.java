package com.tutorial.springboot.firstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	boolean authenticate(String username, String password) {
		
		boolean isValidUser= username.equalsIgnoreCase("Rucha");
		boolean isValidPass= password.equalsIgnoreCase("123");
		return isValidUser && isValidPass;
		
	}
}
