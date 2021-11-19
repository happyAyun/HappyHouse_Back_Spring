package com.ssafy.happyhouse.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ssafy.happyhouse.model.service.OAuthService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final OAuthService oAuthService;

	public SecurityConfig(OAuthService oAuthService) {
		this.oAuthService = oAuthService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//		.csrf().disable() // h2 console 접속을 위해
//				.headers().frameOptions().disable().and() // h2 console 접속을 위해2ㅁ``ㄷ
				.oauth2Login() // OAuth2 로그인 설정 시작점
				.userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
				.userService(oAuthService); // OAuth2 로그인 성공 시, 후작업을 진행할 UserService 인터페이스 구현체 등록
	}

}
