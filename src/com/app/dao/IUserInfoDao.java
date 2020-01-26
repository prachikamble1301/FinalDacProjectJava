package com.app.dao;

import java.util.List;

import com.app.pojos.AssemblyConstituency;
import com.app.pojos.States;
import com.app.pojos.User;
import com.app.pojos.UserInfo;


public interface IUserInfoDao 
{
	 Integer registerVoter(UserInfo userInfo);
	 UserInfo searchDetails(String aadharNo);
	 List<UserInfo> getUserDetails();
	 void updateUser(UserInfo userInfo);
	 void updateByUser(UserInfo userInfo);
	 User getById(int id);
	 
	 List<States> getStates();
	 List<AssemblyConstituency> getAssmList();
	 UserInfo getUser(int regId);
	 AssemblyConstituency getAssembly(int id);
	 String voterId();
	 UserInfo searchForUpdate(String aadharNo);
	
}