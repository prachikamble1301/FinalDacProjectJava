package com.app.controller;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IUserDao;
import com.app.pojos.Otp;
import com.app.pojos.User;
import com.app.pojos.UserRole;
@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private IUserDao dao;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@PostMapping("/register")
	public Integer register(@RequestBody User user)
	{
		
		System.out.println(user);
		user.setRole(UserRole.VOTER);
		
		if(user !=null)
		{
			 
			String msg="Register Successfully Done ";
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
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
		return dao.registerUser(user);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) 
	{
		System.out.println(user);
		return dao.login(user);
    }
	@PostMapping("/forgot")
	public Integer forgotPassword(@RequestBody User user)
	{

		user = dao.findByEmail(user);
		System.out.println(user);
		try
		{		System.out.println(user);
			if(user !=null)
			{
				Otp otp=new Otp();
				otp.setOtp(dao.generateOtp());
				dao.saveOtp(otp);
				String msg="Your one time password for forgot password is = "+otp;
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("One Time Password");
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
				return 1;
			}
		} catch (NoResultException e) 
		{
			System.out.println("in the exception");
			e.printStackTrace();
		}
		return 0;
	}@PostMapping("/confirmOtp")
	public boolean confirmOtp(@RequestBody Otp otp)
	{
		Otp o=dao.getOtp();
		System.out.println(otp.getOtp());
		System.out.println(o.getOtp());
		if(otp.getOtp()==o.getOtp())
		{
			dao.deleteOtp();
			return true;
		}
		else
		{
			System.out.println("in false");
			return false;
		}
	}
	@PostMapping("/resetpassword")
	public boolean resetPassword(@RequestBody User user)
	{	System.out.println(user.getPassword());
	System.out.println(user);
	dao.resetPassword(user);
	
		return true;
	}
}
