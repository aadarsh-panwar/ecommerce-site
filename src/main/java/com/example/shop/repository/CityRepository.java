package com.example.shop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.models.City;

@Repository
public interface CityRepository extends MongoRepository<City, String>{

	City findByNameIgnoreCase(String name);
	List<City> findByStateIgnoreCase(String state);
	boolean existsByNameIgnoreCase(String name);
	boolean existsByStateIgnoreCase(String state);
	boolean existsByNameIgnoreCaseAndStateIgnoreCase(String city, String state);
}
