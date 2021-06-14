package com.example.demo.services;
import java.util.List;
import com.example.demo.dao.StateEntity;

public interface IStateService {

	List<StateEntity> getAllStates();
    boolean addState(StateEntity stateEntity);
    boolean addState(String countryCode, String stateCode, String name, int population);
    void updateState(StateEntity stateEntity);
    void deleteState(String stateCode);
	StateEntity getStateByStateCode(String stateCode);
	List<StateEntity> getStateByCountryCode(String countryCode);
	int getAllCountriesPopulation();
	int getCountryPopulation(String CountryCode);
	boolean validateState(String countryCode, String stateCode);
}
