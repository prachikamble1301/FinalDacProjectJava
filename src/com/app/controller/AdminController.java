package com.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IUserInfoDao;
import com.app.pojos.UserInfo;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/admin")
public class AdminController 
{
	String id;
	String msg;
	@Autowired
	private IUserInfoDao uidao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public AdminController() {
		System.out.println("inside admin controller");
	}
	
	@GetMapping
	public List<UserInfo> getUserDetails()
	{
		System.out.println("in getUserDetails");
		System.out.println(uidao.getUserDetails());
		return uidao.getUserDetails();
	}
	@GetMapping("/user")
	public UserInfo getUser(@RequestParam String regId)
	{
		System.out.println("get user");
		
		
		return uidao.getUser(Integer.parseInt(regId));
	}
	@PutMapping()
	public void updateUserStatus(@RequestBody UserInfo userInfo)
	{
		
		if(userInfo.getStatus().equals("true"))
		{
			id=uidao.voterId();
			userInfo.setVoterId(id);
			msg="Successfully Registered as Voter. Your VoterId id is  "+id;
		}
		else
		{
			msg="Can't registered as Voter..There is some problem with your documents.. plzz checked them";
		}
		uidao.updateUser(userInfo);
		
		 
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userInfo.getUser().getEmail());
		mailMessage.setSubject("Election Commission of India");
		mailMessage.setText(msg);
		try
		{
			mailSender.send(mailMessage);
		}
		catch (MailException e) 
		{
			System.out.println("inside mail exception");
			e.printStackTrace();
		}
	}
}
