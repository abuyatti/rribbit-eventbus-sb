package com.example;

import java.math.BigDecimal;

import org.rribbit.RequestResponseBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired
	private RequestResponseBus rrb;

	@RequestMapping("/order")
	public String submitOrder() {
		Integer userId = 1;
		BigDecimal amount = BigDecimal.TEN;
		rrb.send("processPayment", userId, amount);
		return "Order Submitted";
	}

}
