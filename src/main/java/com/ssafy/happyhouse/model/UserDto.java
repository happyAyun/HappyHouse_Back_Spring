package com.ssafy.happyhouse.model;

public class UserDto {

	private String userId;
	private String email;
	private String password;
	private String name;
	private String joinDate;

	public UserDto() {
	}

	public UserDto(String userId, String email, String password, String name, String joinDate) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.name = name;
		this.joinDate = joinDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", joinDate=" + joinDate + "]";
	}

}
