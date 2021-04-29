package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.shop.models.*;
import java.util.*;
import com.example.shop.services.PincodeService;

@RestController
@CrossOrigin
@RequestMapping("/pincode")
public class PincodeController {
	private PincodeService pincodeService;
	
	@Autowired 
	public PincodeController(PincodeService pincodeService)
	{
		this.pincodeService = pincodeService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Pincode> getAll()
	{
		return pincodeService.getAll();
	}
}
