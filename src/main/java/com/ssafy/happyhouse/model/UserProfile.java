package com.ssafy.happyhouse.model;

public class UserProfile {
	private final String oauthId;
	private final String name;
	private final String email;

	public UserProfile(String oauthId, String name, String email) {
		this.oauthId = oauthId;
		this.name = name;
		this.email = email;
	}

	public Member toMember() {
		return new Member(oauthId, name, email, Role.USER);
	}

	public String getOauthId() {
		return oauthId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}