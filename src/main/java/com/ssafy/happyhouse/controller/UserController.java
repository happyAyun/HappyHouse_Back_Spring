package com.ssafy.happyhouse.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/idcheck")
	@ResponseBody
	public ResponseEntity<Integer> idCheck(@RequestParam("ckid") String checkId) throws Exception {
		logger.debug("checkid : {}", checkId);
		int idCount = userService.idCheck(checkId);
		logger.debug("idCount : {}", idCount);
		return new ResponseEntity<Integer>(idCount, HttpStatus.OK);
		// return idCount; /// ??????? json으로 하지 않았음.. - 잭슨?
	}

	@GetMapping("/regist")
	public String regist() {
		return "user/join";
	}

	@PostMapping("/regist")
	public String regist(UserDto userDto) throws Exception {
		userService.registerUser(userDto);
		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
			HttpServletResponse response) throws Exception {
		logger.debug("map : {}", map.get("userId"));
		UserDto userDto = userService.login(map);
		if (userDto != null) {
			session.setAttribute("userinfo", userDto);

			Cookie cookie = new Cookie("userid", map.get("userId"));
			cookie.setPath("/");
			if ("saveok".equals(map.get("idsave"))) {
				cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
			} else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			return "redirect:/";
		} else {
			model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
			return "user/login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/list")
	public String list() {
		return "user/list";
	}

	@GetMapping("/info")
	public String info() {
		return "user/info";
	}

	@GetMapping("/myinfo")
	@ResponseBody
	public ResponseEntity<UserDto> myInfo(HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		String userId = userDto.getUserId();
		userDto = userService.getUser(userId);
		logger.debug("userDto : {}", userDto.toString());
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@PutMapping("/modify")
	@ResponseBody
	public ResponseEntity<UserDto> modify(@RequestBody UserDto userDto, HttpSession session) throws Exception {
		logger.debug("userDto : {}", userDto.toString());
		userDto = userService.updateUser(userDto);
		session.setAttribute("userinfo", userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}

	@GetMapping("/delete")
	public String delete(HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userinfo");
		String userId = userDto.getUserId();
		userService.deleteUser(userId);
		session.invalidate();
		return "redirect:/";
	}
}
