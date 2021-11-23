package com.ssafy.happyhouse.oauth2;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.controller.MemberController;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;

@CrossOrigin
@RestController()
@RequestMapping("/kakao")
public class KakaoController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

//	@Value("${cos.key}")
//	private String cosKey;

	private static final String CLIENT_ID = "ad49192b4c4d8d6e220c38d63d62206b";
	private static final String CLIENT_SECRET = "Rkr6ajMevhfmbLcCpz15s5NrPgLJkLZv";
	private static final String REDIRECT_URL = "http://localhost:8080/kakao/auth";

	@GetMapping("/login/{code}")
	public ResponseEntity<Map<String, Object>> auth(@PathVariable("code") String code) throws Exception {
		System.out.println("hi auth");
		System.out.println(code);
		RestTemplate rt = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", CLIENT_ID);
		params.add("redirect_uri", REDIRECT_URL);
		params.add("code", code);
		params.add("client_secret", CLIENT_SECRET);

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		System.out.println(response.getBody());

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		OauthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OauthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RestTemplate rt2 = new RestTemplate();

		// HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

		// Http 요청하기 - Post방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest, String.class);

		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println(kakaoProfile.getId());
		System.out.println(kakaoProfile.getKakao_account().getEmail());
		System.out.println(kakaoProfile.getProperties().getNickname());
		// UUID란 -> 중복되지 않음.
		// UUID garbagePassword = UUID.randomUUID();
//		System.out.println();

		MemberDto memberDto = MemberDto.builder().userid(kakaoProfile.getId())
				.username(kakaoProfile.getProperties().getNickname()).userpwd("123qwe")
				.email(kakaoProfile.getKakao_account().getEmail()).build();
		// .userpwd(kakaoProfile.getId() + kakaoProfile.getKakao_account().getEmail())

		// 가입자 혹은 비가입자 체크해서 처리
		if (memberService.checkId(kakaoProfile.getId())) { // 가입X
			System.out.println("가입하지 않은 새로운 회원");
			memberService.joinUser(memberDto);
		}

		System.out.println("가입된 회원");
		// 로그인 처리
		MemberDto loginUser = memberService.login(memberDto);
		System.out.println(loginUser);

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		System.out.println(resultMap.toString());
		System.out.println(status);

//		MemberDto front = new MemberDto(loginUser.getUserid(), loginUser.getUsername(), loginUser.getEmail(),
//				loginUser.getJoindate());
//		ObjectMapper objecMapper = new ObjectMapper();
//		String personJson = objecMapper.writeValueAsString(front);

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		// return "회원가입 및 로그인 처리 완료";
	}
}