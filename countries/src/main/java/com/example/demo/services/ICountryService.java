package com.example.demo.services;
import java.util.List;
import com.example.demo.dao.CountryEntity;

public interface ICountryService {

	List<CountryEntity> getAllCountries();
    boolean addCountry(CountryEntity countryEntity);
    void updateCountry(CountryEntity countryEntity);
    void deleteCountry(String CountryCode);
	CountryEntity getCountryByCountryCode(String countryCode);
	
}
