package com.example;

import java.math.BigDecimal;

import org.rribbit.Listener;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	@Listener(hint = "processPayment")
	public void doPayment(Integer userId, BigDecimal amount) {
		System.out.println("Do payment...");
	}

}
