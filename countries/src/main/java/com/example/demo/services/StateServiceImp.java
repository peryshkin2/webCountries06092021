package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StateEntity;
import com.example.demo.dao.MyStateRepository;

@Service
public class StateServiceImp implements IStateService {
	@Autowired
	private MyStateRepository stateRepository;

	@Override
	public StateEntity getStateByStateCode(String stateCode) {
		StateEntity obj = stateRepository.findByStateCode(stateCode).get();
		return obj;
	}

	@Override
	public List<StateEntity> getAllStates() {
		List<StateEntity> list = new ArrayList<>();
		stateRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public List<StateEntity> getStateByCountryCode(String countryCode) {
		List<StateEntity> list = new ArrayList<>();
		stateRepository.findByCountryCode(countryCode).forEach(e -> list.add(e));
		return list;
	}

	@Override
	public synchronized boolean addState(StateEntity stateEntity) {
		if (stateRepository.findByStateCode(stateEntity.getStateCode()).isPresent()) {
			return false;
		} else {
			stateRepository.save(stateEntity);
			return true;
		}
	}

	@Override
	public synchronized boolean addState(String countryCode, String stateCode, String name, int population) {

		StateEntity stateEntity = new StateEntity(countryCode, stateCode, name, population);
		if (stateRepository.findByStateCode(stateEntity.getStateCode()).isPresent()) {
			return false;
		} else {
			stateRepository.save(stateEntity);
			return true;
		}
	}

	@Override
	public synchronized void updateState(StateEntity stateEntity) {
		stateRepository.save(stateEntity);
	}

	@Override
	public void deleteState(String stateEntityId) {
		stateRepository.delete(getStateByStateCode(stateEntityId));
	}

	@Override
	public int getAllCountriesPopulation() {
		List<StateEntity> listStates = getAllStates();
		return listStates.stream().map(s -> s.getPopulation()).mapToInt(Number::intValue).sum();
	}

	@Override
	public int getCountryPopulation(String countryCode) {
		List<StateEntity> listStates = getStateByCountryCode(countryCode);
		return listStates.stream().map(s -> s.getPopulation()).mapToInt(Number::intValue).sum();
	}

	@Override
	public boolean validateState(String countryCode, String stateCode) {
		StateEntity state = getStateByStateCode(stateCode);
		return state.getCountryCode().equals(countryCode);
	}
}
