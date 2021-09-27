package com.example;

import java.math.BigDecimal;

import org.rribbit.Listener;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Listener(hint = "getUserEmail")
	public String getEmailAddress(Integer userId) {
		return "foo@bar.com";
	}

	@Listener(hint = "processPayment")
	public void registerPayment(Integer userId, BigDecimal amount) {
		System.out.println("Register payment in database...");
	}

}
