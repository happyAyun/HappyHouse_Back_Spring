package com.ssafy.happyhouse.oauth;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	private final HttpSession httpSession = null;

	@GetMapping("/")
	public String index(Model model) {

		SessionUser user = (SessionUser) httpSession.getAttribute("user");

		if (user != null) {
			model.addAttribute("userName", user.getName());
		}

		return "index";
	}
}