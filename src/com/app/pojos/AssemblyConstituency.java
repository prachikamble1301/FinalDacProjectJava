package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AssemblyConstituency 
{
	private Integer assId;
	private String name;
	private List<UserInfo> uIList=new ArrayList<UserInfo>();
	private Districts distId;
	
	 public AssemblyConstituency() {
		// TODO Auto-generated constructor stub
	}

	

	public AssemblyConstituency(String name) {
		super();
		this.name = name;
		
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAssId() {
		return assId;
	}

	public void setAssId(Integer assId) {
		this.assId = assId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@ManyToOne
	@JoinColumn(name="distId")
	public Districts getDistId() {
		return distId;
	}

	public void setDistId(Districts distId) {
		this.distId = distId;
	}

	
	@JsonBackReference
	@OneToMany(mappedBy = "assmId" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	public List<UserInfo> getuIList() {
		return uIList;
	}



	public void setuIList(List<UserInfo> uIList) {
		this.uIList = uIList;
	}



	@Override
	public String toString() {
		return "AssemblyConstituency [assId=" + assId + ", name=" + name + ", distId=" + distId + "]";
	}

	

	
	 
	 
}
