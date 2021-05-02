package com.example.shop.controllers;

import java.util.List;

import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.City;
import com.example.shop.services.CityService;

@RestController
@CrossOrigin
@RequestMapping("/city")
public class CityController {
	private CityService cityService;
	
	public CityController(CityService cityService)
	{
		this.cityService = cityService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Slice<City> getAll(@RequestParam(defaultValue= "0")int page, 
							  @RequestParam(defaultValue ="20")int size)
	{
		return cityService.getAll(page, size);
	}
	@RequestMapping(value = "/name/{city}", method = RequestMethod.GET)
	public City getByCity(@PathVariable("city")String city)
	{
		return cityService.getByName(city);
	}
	@RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
	public List<City> getByState(@PathVariable("state")String state)
	{
		return cityService.getByState(state);
	}
	@RequestMapping(value = "/check/name/{name}", method = RequestMethod.GET)
	public boolean existsByName(@PathVariable("name")String name)
	{
		return cityService.existsByName(name);
	}
	@RequestMapping(value = "/check/state/{state}",method = RequestMethod.GET)
	public boolean existsByState(@PathVariable("state")String state)
	{
		return cityService.existsByState(state);
	}
	@RequestMapping(value = "/check/name/state/{name}/{state}", method = RequestMethod.GET)
	public boolean existsByNameAndState(@PathVariable("name")String name, 
										@PathVariable("state")String state)
	{
		return cityService.existsByNameAndState(name, state);
	}
	
}
