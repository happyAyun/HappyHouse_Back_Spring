package com.ssafy.happyhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/move")
public class MainController {

	@GetMapping("/home")
	public String home() {
		return "redirect:/";
	}

	@GetMapping("/map")
	public String map() {
		return "apt/viewAPT";
	}

}
