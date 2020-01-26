package com.app.pojos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class User 
{
	private Integer userId;
	private String email;
	private String mobile;
	private String password;
	private UserRole role;
	private UserInfo userInfo;
	
	public User() {
		System.out.println("in user class ctor");
	}


	public User(String email, String mobile, String password, UserRole role) {
		super();
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Enumerated(EnumType.STRING)
	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}

	@JsonBackReference
	@OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL, orphanRemoval = true)
	public UserInfo getUserInfo() {
		return userInfo;
	}


	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	


	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", mobile=" + mobile + "]";
	}


	
	
}
