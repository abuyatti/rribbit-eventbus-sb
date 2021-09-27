package com.example;

import java.math.BigDecimal;

import org.rribbit.Listener;
import org.rribbit.RequestResponseBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private RequestResponseBus rrb;

	@Listener(hint = "processPayment")
	public void sendPaymentEmail(Integer userId, BigDecimal amount) {
		String emailAddress = rrb.send("getUserEmail", userId);
		System.out.println("Send email to... " + emailAddress);
	}

}
