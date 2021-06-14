package com.example.demo;

//import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;
import static java.util.stream.Collectors.toList;

import com.example.demo.dao.CountryEntity;
import com.example.demo.dao.StateEntity;
import com.example.demo.services.ICountryService;
import com.example.demo.services.IStateService;
import com.example.demo.utils.CountryCurrency;

@Controller
@RequestMapping("user")
public class CountriesController {
	@Autowired
	private ICountryService countryService;
	
	@Autowired
	private IStateService stateService;
	
	@GetMapping("country/{id}")
	public ResponseEntity<CountryEntity> getCountryByCountryCode(@PathVariable("id") String id) {
		CountryEntity countryEntity = countryService.getCountryByCountryCode(id);
		return new ResponseEntity<CountryEntity>(countryEntity, HttpStatus.OK);
	}
	
	@GetMapping("countries")
	public ResponseEntity<List<CountryEntity>> getAllCountries() {
		List<CountryEntity> list = countryService.getAllCountries();
		return new ResponseEntity<List<CountryEntity>>(list, HttpStatus.OK);
	}
	
	@PostMapping("country")
	public ResponseEntity<Void> addCountry(@RequestBody CountryEntity countryEntity, UriComponentsBuilder builder) {
                boolean flag = countryService.addCountry(countryEntity);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/country/{id}").buildAndExpand(countryEntity.getCountryCode()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("country")
	public ResponseEntity<CountryEntity> updateCountry(@RequestBody CountryEntity countryEntity) {
		countryService.updateCountry(countryEntity);
		return new ResponseEntity<CountryEntity>(countryEntity, HttpStatus.OK);
	}
	
	@DeleteMapping("country/{id}")
	public ResponseEntity<Void> deleteCountry(@PathVariable("id") String id) {
		countryService.deleteCountry(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
	// --------------- State --------------------
	@GetMapping("state/{id}")
	public ResponseEntity<StateEntity> getStateByStateCode(@PathVariable("id") String id) {
		StateEntity stateEntity = stateService.getStateByStateCode(id);
		return new ResponseEntity<StateEntity>(stateEntity, HttpStatus.OK);
	}
	
	@GetMapping("states")
	public ResponseEntity<List<StateEntity>> getAllStates() {
		List<StateEntity> list = stateService.getAllStates();
		return new ResponseEntity<List<StateEntity>>(list, HttpStatus.OK);
	}
	
	@PostMapping("state")
	public ResponseEntity<Void> addState(@RequestBody StateEntity stateEntity, UriComponentsBuilder builder) {
                boolean flag = stateService.addState(stateEntity);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/state/{id}").buildAndExpand(stateEntity.getStateCode()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("state")
	public ResponseEntity<StateEntity> updateState(@RequestBody StateEntity stateEntity) {
		stateService.updateState(stateEntity);
		return new ResponseEntity<StateEntity>(stateEntity, HttpStatus.OK);
	}
	
	@DeleteMapping("state/{id}")
	public ResponseEntity<Void> deleteState(@PathVariable("id") String id) {
		stateService.deleteState(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
	
	//--------- combined ------------------
	
	@GetMapping("totalpopulation")
	public ResponseEntity<Integer> getAllCountriesPopulation() {
		Integer population = stateService.getAllCountriesPopulation();
		return new ResponseEntity<Integer>(population, HttpStatus.OK);
	}
	
	@GetMapping("countrypopulation")
	public ResponseEntity<Integer> getCountryPopulation(@RequestParam(name="country_code") String countryCode) {
		Integer population = stateService.getCountryPopulation(countryCode);
		return new ResponseEntity<Integer>(population, HttpStatus.OK);
	}
	
	@GetMapping("currencies")
	public ResponseEntity<List<CountryCurrency>> getAllCountryCurrencies() {
		List<CountryEntity> list = countryService.getAllCountries();
		List<CountryCurrency> listCurrency = list.stream().map(c-> new CountryCurrency(c.getCountryCode(), c.getCurrency())).collect(toList());
		return new ResponseEntity<List<CountryCurrency>>(listCurrency, HttpStatus.OK);
	}
	
	@GetMapping("validatestate")
	public ResponseEntity<Boolean> validateState(@RequestParam(name="country_code") String countryCode, @RequestParam(name="state_code") String stateCode) {
		Boolean flag = stateService.validateState(countryCode, stateCode);
		if(flag) {
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(flag, HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("addstate")
	public ResponseEntity<Void> addState(@RequestParam(name="country_code") String countryCode,
			@RequestParam(name="state_code") String stateCode,
			@RequestParam(name="state_name") String name,
			@RequestParam(name="state_population") int population,
			UriComponentsBuilder builder) {
		
		Boolean flag = stateService.addState(countryCode, stateCode, name, population);
		if (flag == false) {
     	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
             }
             HttpHeaders headers = new HttpHeaders();
             headers.setLocation(builder.path("/addstate").buildAndExpand(stateCode).toUri());
             return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
} 
