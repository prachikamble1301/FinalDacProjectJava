package com.app.dao;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.AssemblyConstituency;
import com.app.pojos.States;
import com.app.pojos.User;
import com.app.pojos.UserInfo;


import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Service
@Transactional
public class UserInfoDaoImpl implements IUserInfoDao {

	
	@Autowired
	private SessionFactory sf;
	
	
	@Override
	public Integer registerVoter(UserInfo userInfo) {
		
		return (Integer) sf.getCurrentSession().save(userInfo);
	}


	@Override
	public UserInfo searchDetails(String ad) {
		System.out.println(ad);
		String jpql="select uf from UserInfo uf where uf.aadharNo=:ad and uf.status='true'"; 
		return sf.getCurrentSession().createQuery(jpql,UserInfo.class).setParameter("ad",ad).getSingleResult();
	}


	@Override
	public List<UserInfo> getUserDetails() {
		String jpql="select u from UserInfo u where status='false'";
		return sf.getCurrentSession().createQuery(jpql,UserInfo.class).getResultList() ;
	}


	@Override
	public void updateUser(UserInfo userInfo) {
		Session hs = sf.getCurrentSession();		
		hs.clear();
		
		hs.update(userInfo);
		
	}


	@Override
	public void updateByUser(UserInfo userInfo) 
	{
		
		Session hs = sf.getCurrentSession();		
		hs.clear();
		userInfo.setStatus("false");
		hs.update(userInfo);
		
	}


	@Override
	public User getById(int id) {
		
		return sf.getCurrentSession().get(User.class,id);
	}


	@Override
	public List<States> getStates() {
		String jpql="select s from States s";
		return sf.getCurrentSession().createQuery(jpql,States.class).getResultList() ;
		
	}


	@Override
	public List<AssemblyConstituency> getAssmList() {
		String jpql = "Select a from AssemblyConstituency a";
		return sf.getCurrentSession().createQuery(jpql,AssemblyConstituency.class).getResultList();
	}


	@Override
	public UserInfo getUser(int regId) {

		return sf.getCurrentSession().get(UserInfo.class, regId);
	}


	@Override
	public AssemblyConstituency getAssembly(int id) {
		
		return sf.getCurrentSession().get(AssemblyConstituency.class, id);
	}


	@Override
	public String voterId() {
		
		Random random = new Random();
		int num = random.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) 
		{
			num = random.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999)
			{
				System.out.println("Unable to generate PIN at this time..");
			}
		}
		String id = "IND-Mh"+num;
		return id;
		
	}


	@Override
	public UserInfo searchForUpdate(String ad) {
		String jpql="select uf from UserInfo uf where uf.aadharNo=:ad"; 
		return sf.getCurrentSession().createQuery(jpql,UserInfo.class).setParameter("ad",ad).getSingleResult();
	}
	
	

}
