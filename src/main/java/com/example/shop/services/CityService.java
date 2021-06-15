package com.example.shop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.shop.models.City;
import com.example.shop.repository.CityRepository;

@Service
public class CityService {
	private CityRepository cityRepository;
	
	@Autowired
	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}
	
	public Slice<City> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page,  size);
		Slice<City> slice = cityRepository.findAll(pageable);
		return slice;
	}
	public City getByName(String name) {
		return cityRepository.findByNameIgnoreCase(name);
	}
	public List<City> getByState(String state) {
		return cityRepository.findByStateIgnoreCase(state);
	}
	public boolean existsByName(String name) {
		return cityRepository.existsByNameIgnoreCase(name);
	}
	public boolean existsByState(String state) {
		return cityRepository.existsByStateIgnoreCase(state);
	}
	public boolean existsByNameAndState(String name, String state) {
		return cityRepository.existsByNameIgnoreCaseAndStateIgnoreCase(name, state);
	}
}
