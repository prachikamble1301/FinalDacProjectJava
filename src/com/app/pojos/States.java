package com.app.pojos;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class States 
{
	private Integer sId;
	private String stateName;
	private List<Districts> distList = new  ArrayList<>();
	
	public States() {
		// TODO Auto-generated constructor stub
	}


	public States(String stateName) {
		super();
		this.stateName = stateName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getsId() {
		return sId;
	}

	
	public void setsId(Integer sId) {
		this.sId = sId;
	}


	public String getStateName() {
		return stateName;
	}


	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@JsonBackReference
	@OneToMany(mappedBy = "state" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	public List<Districts> getDistList() {
		return distList;
	}


	public void setDistList(List<Districts> distList) {
		this.distList = distList;
	}


	@Override
	public String toString() {
		return "States [sId=" + sId + ", stateName=" + stateName + "]";
	}


	
	
}

