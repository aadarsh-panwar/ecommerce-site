package com.example.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.Pincode;
import com.example.shop.services.PincodeService;

@RestController
@CrossOrigin
@RequestMapping("/pincode")
public class PincodeController {
	private PincodeService pincodeService;
	
	@Autowired 
	public PincodeController(PincodeService pincodeService) {
		this.pincodeService = pincodeService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Slice<Pincode> getAll(@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue = "20")int size)	{
		return pincodeService.getAll(page, size);
	}
	
	@RequestMapping(value = "/division-name/{divisionName}", method = RequestMethod.GET)
	public Slice<Pincode> getByDivisionName(@PathVariable("divisionName")String divisionName,
											@RequestParam(defaultValue = "0")int page,
											@RequestParam(defaultValue = "20")int size) {
		return pincodeService.getByDivisionName(divisionName, page, size);
	}
	
	@RequestMapping(value = "/office-name/{officeName}", method = RequestMethod.GET)
	public List<Pincode> getByOfficeName(@PathVariable("officeName")String officeName) {
		return pincodeService.getByOfficeName(officeName);
	}
	
	@RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
	public List<Pincode> getByCode(@PathVariable("code")int code) {
		return pincodeService.getByCode(code);
	}
	
	@RequestMapping(value = "/district/{district}", method = RequestMethod.GET)
	public Slice<Pincode> getByDistrict(@PathVariable("district")String district,
										@RequestParam(defaultValue = "0")int page, 
										@RequestParam(defaultValue = "20")int size) {
		return pincodeService.getByDistrict(district, page, size);
	}
	
	@RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
	public Slice<Pincode> getByState(@PathVariable("state")String state,
									 @RequestParam(defaultValue ="0")int page, 
									 @RequestParam(defaultValue = "20")int size) {
		return pincodeService.getByState(state, page, size);
	}
}
