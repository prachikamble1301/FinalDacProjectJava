package com.app.pojos;

import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class UserInfo 
{
	  private Integer regId;
	  private String name;
	  private String surname;
	  private String gender;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date boD;
	  private String street;
	  private String villege;
	  private String aadharNo;
	  private AssemblyConstituency assmId;
	  private String status;
	  private User user;
	  private String voterId;
	  private byte[] image;
	  
	  public UserInfo() {
		System.out.println("inside user pojo");
	}
	  

	public UserInfo(String name, String surname, String gender, Date boD, String street, String villege,
			String aadharNo, String status, String voterId) {
		super();
		
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.boD = boD;
		this.street = street;
		this.villege = villege;
		this.aadharNo = aadharNo;
		this.status = status;
		this.voterId = voterId;
	}

	public UserInfo(String name, String surname, String gender,Date boD, String street, String villege,
			String aadharNo) {
		super();
		
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.boD = boD;
		this.street = street;
		this.villege = villege;
		this.aadharNo = aadharNo;
		//this.status = "false,";
		//this.voterId = voterId;
	}

	public UserInfo(String name, String surname, String gender, Date boD, String street, String villege,
			String aadharNo, AssemblyConstituency assmId, String status, User user, String voterId) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		boD = boD;
		this.street = street;
		this.villege = villege;
		this.aadharNo = aadharNo;
		this.assmId = assmId;
		this.status = status;
		this.user = user;
		this.voterId = voterId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRegId() {
		return regId;
	}


	public void setRegId(Integer regId) {
		this.regId = regId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	public Date getBoD() {
		return boD;
	}


	public void setBoD(Date boD) {
		this.boD = boD;
	}




	public String getStreet() {
		return street;
	}


	
	public void setStreet(String street) {
		this.street = street;
	}


	public String getVillege() {
		return villege;
	}


	public void setVillege(String villege) {
		this.villege = villege;
	}

	@Column(unique = true)
	public String getAadharNo() {
		return aadharNo;
	}


	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	@ManyToOne
	@JoinColumn(name="assmId")
	public AssemblyConstituency getAssmId() {
		return assmId;
	}


	public void setAssmId(AssemblyConstituency assmId) {
		this.assmId = assmId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	
	@OneToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		
		this.user = user;
	}

	
	public String getVoterId() {
		return voterId;
	}


	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	
	
	@Lob
	@Column(length=16777215)
	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "UserInfo [regId=" + regId + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", BoD="
				+ boD + ", street=" + street + ", villege=" + villege + ", aadharNo=" + aadharNo + ", assmId=" + assmId
				+ ", status=" + status + ", voterId=" + voterId + "]";
	}


	

	  
	  
  
}
