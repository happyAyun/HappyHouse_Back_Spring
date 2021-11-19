package com.ssafy.happyhouse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

	@GetMapping("/")
	public String Hello() {
		return "Hello World";
	}

	@GetMapping("/restricted")
	public String restricted() {
		return "restricted";
	}

}
