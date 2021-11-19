package com.ssafy.happyhouse.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String oauthId;

	private String name;

	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	protected Member() {
	}

	public Member(String oauthId, String name, String email, Role role) {
		this(null, oauthId, name, email, role);
	}

	public Member(Long id, String oauthId, String name, String email, Role role) {
		this.id = id;
		this.oauthId = oauthId;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public Member update(String name, String email) {
		this.name = name;
		this.email = email;
		return this;
	}

	public String getRoleKey() {
		return this.role.getKey();
	}

	public Long getId() {
		return id;
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

	public Role getRole() {
		return role;
	}
}