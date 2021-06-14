package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MyStateRepository extends CrudRepository<StateEntity, String> {

	Optional<StateEntity> findByStateCode(String stateCode);
	List<StateEntity> findByCountryCode(String countryCode);
}
