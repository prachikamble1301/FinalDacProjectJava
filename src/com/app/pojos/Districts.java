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


@Entity
public class Districts 
{
		private Integer dId;
		private String disName;
		private States state;
		private List<AssemblyConstituency> assList = new ArrayList<>(); 
		
	public Districts() {
		// TODO Auto-generated constructor stub
	}

	public Districts(String disName) {
		super();
		this.disName = disName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getdId() {
		return dId;
	}

	public void setdId(Integer dId) {
		this.dId = dId;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	
	@ManyToOne
	@JoinColumn(name="stateId")
	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}
	@JsonBackReference
	@OneToMany(mappedBy = "distId" ,cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	public List<AssemblyConstituency> getAssList() {
		return assList;
	}

	public void setAssList(List<AssemblyConstituency> assList) {
		this.assList = assList;
	}

	@Override
	public String toString() {
		return "Districts [dId=" + dId + ", disName=" + disName + ", state=" + state +  "]";
	}

	
	

}
