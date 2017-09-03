package com.tacsio.bdd.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello/{user}")
	public String hello(@PathVariable String user) {

		String msg = null;

		if (user.equalsIgnoreCase("BB8")) {
			msg = "wawawawi";
		} else {
			msg = String.format("Olá, %s", user);
		}
		return msg;
	}
}
