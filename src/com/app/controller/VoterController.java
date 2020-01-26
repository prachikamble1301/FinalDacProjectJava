package com.app.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.IUserInfoDao;
import com.app.pojos.AssemblyConstituency;

import com.app.pojos.User;
import com.app.pojos.UserInfo;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/voter")
public class VoterController {

	@Autowired
	private IUserInfoDao uidao;

	@Autowired
	private JavaMailSender mailSender;

	public VoterController() {
		System.out.println("inside Voter Controller");
	}

	@GetMapping("/registers")
	public List<AssemblyConstituency> getAssmList() {

		System.out.println(uidao.getAssmList());
		List<AssemblyConstituency> asmList = uidao.getAssmList();
		List<AssemblyConstituency> asmListRef = new ArrayList<AssemblyConstituency>();
		for (AssemblyConstituency asm : asmList) {
			AssemblyConstituency asmcon = new AssemblyConstituency();
			asmcon.setAssId(asm.getAssId());
			asmcon.setName(asm.getName());
			asmcon.setDistId(asm.getDistId());
			System.out.println(asmcon);
			asmListRef.add(asmcon);
		
		}

		
		return asmListRef;
//		return uidao.getAssmList();
		
	}

		  
		  @PostMapping("/register")
			public ResponseEntity<?> register(@RequestParam String name, @RequestParam String surname,@RequestParam String gender,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date boD,@RequestParam String street,@RequestParam String villege,
					@RequestParam String aadharNo,  @RequestParam int uid,@RequestParam int assId, @RequestParam(value = "image", required = false) MultipartFile image) {

				System.out.println(boD);
				
			  UserInfo uif = new UserInfo( name,  surname, gender,boD, street, villege,
					 aadharNo);
				User user = uidao.getById(uid);
				AssemblyConstituency asm = uidao.getAssembly(assId);
				
				uif.setUser(user);
				uif.setAssmId(asm);
				uif.setStatus("false");
				if (image != null) {
					try {
						System.out.println(image.getOriginalFilename());
						uif.setImage(image.getBytes());
						System.out.println(uif);
						return new ResponseEntity<Integer>(uidao.registerVoter(uif),HttpStatus.CREATED);
					} catch (Exception e) {

						return new ResponseEntity<UserInfo>(new UserInfo(),HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
			  if(uif !=null) 
			  {
				  String msg="Voter Registration Successfully Done "; 
				  SimpleMailMessage mailMessage = new SimpleMailMessage(); 
				  mailMessage.setTo(user.getEmail());
				  mailMessage.setSubject("Election Commission of India");
				  mailMessage.setText(msg);
				  try
				  { 
					  mailSender.send(mailMessage); 
				 } 
				   catch(MailException e) 
				  { 
					  System.out.println("inside mail exception");
				       e.printStackTrace(); 
				  }
				  
			  }
	 
		return null;
	}

	@PutMapping("/search")
	public UserInfo findMe(@RequestBody UserInfo ui) {
		System.out.println(ui.getAadharNo());
		return uidao.searchDetails(ui.getAadharNo());
	}

	@PutMapping("/update")
	public void updatebyUser(@RequestBody UserInfo userInfo) {

		System.out.println(userInfo);
		uidao.updateByUser(userInfo);
	}
	
	@PutMapping("/searchForUpdate")
	public UserInfo find(@RequestBody UserInfo ui)
	{
		System.out.println(ui.getAadharNo());
		return uidao.searchForUpdate(ui.getAadharNo());
	}
}
