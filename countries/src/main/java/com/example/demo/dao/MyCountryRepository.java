package com.example.demo.dao;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MyCountryRepository extends CrudRepository<CountryEntity, String> {

	Optional<CountryEntity> findByCountryCode(String countryCode);
}
