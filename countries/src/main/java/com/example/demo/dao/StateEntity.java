package com.example.demo.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="states")
public class StateEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="state_code")
	private String stateCode;
	@Column(name="state")
	private String stateName;
	@Column(name="country_code")
	private String countryCode;
	@Column(name="population")
	private int population;
	
	public StateEntity() {
		
	}
	
	public StateEntity(String countryCode, String stateCode, String name, int population){
		this.stateCode = stateCode;
		this.countryCode = countryCode;
		this.stateName = name;
		this.population = population;
	}
	
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}

}
