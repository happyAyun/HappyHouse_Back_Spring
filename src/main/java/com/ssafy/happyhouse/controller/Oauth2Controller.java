package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Oauth2Controller {

	@GetMapping("/")
	public String index() {
		return "index";
	}

}
