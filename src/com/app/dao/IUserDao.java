package com.app.dao;

import com.app.pojos.Otp;
import com.app.pojos.User;

public interface IUserDao 
{
	public Integer registerUser(User user);
	public User login(User user);
	public int generateOtp();
	public User findByEmail(User user);
	public User getUserByid(int userid);
	public User findByEmailadd(String email);
	public void saveOtp(Otp otp);
	public Otp getOtp();
	public void deleteOtp();
	public void resetPassword(User user);
	
}
