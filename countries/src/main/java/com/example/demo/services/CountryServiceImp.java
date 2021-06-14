package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CountryEntity;
import com.example.demo.dao.MyCountryRepository;

@Service
public class CountryServiceImp implements ICountryService {
	@Autowired
	private MyCountryRepository countryRepository;
	@Override
	public CountryEntity getCountryByCountryCode(String countryCode) {
		CountryEntity obj = countryRepository.findByCountryCode(countryCode).get();
		return obj;
	}	
	
	@Override
	public List<CountryEntity> getAllCountries(){
		List<CountryEntity> list = new ArrayList<>();
		countryRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public synchronized boolean addCountry(CountryEntity CountryEntity){
	        if( countryRepository.findByCountryCode(CountryEntity.getCountryCode()).isPresent()) {	
    	           return false;
                } else {
    	        countryRepository.save(CountryEntity);
    	        return true;
       }
	}
	
	@Override
	public void updateCountry(CountryEntity CountryEntity) {
		countryRepository.save(CountryEntity);
	}
	
	@Override
	public void deleteCountry(String CountryEntityId) {
		countryRepository.delete(getCountryByCountryCode(CountryEntityId));
	}
	
//	@Override
//	public int getAllCountriesPopulation() {
//		List<StateEntity> listStates = getAllStates();
//		
//		
//		return n;
//	}
} 
