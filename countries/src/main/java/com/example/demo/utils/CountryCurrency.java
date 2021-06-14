package com.example.demo.utils;

public class CountryCurrency {
	private String countryCode;
	private String countryCurrency;
	
	public CountryCurrency(String code, String curr){
		this.countryCode = code;
		this.countryCurrency = curr;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryCurrency() {
		return countryCurrency;
	}
	public void setCountryCurrency(String countryCurrency) {
		this.countryCurrency = countryCurrency;
	}

}
