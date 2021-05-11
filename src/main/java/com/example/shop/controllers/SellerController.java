package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.Seller;
import com.example.shop.services.SellerService;

@RestController
@CrossOrigin
@RequestMapping("/seller")
public class SellerController {
	private SellerService sellerService;
	
	@Autowired
	public SellerController(SellerService sellerService)
	{
		this.sellerService = sellerService;
	}
	
	@RequestMapping(value = "/all", method =RequestMethod.GET)
	public Slice<Seller> getAll(@RequestParam(defaultValue = "0")int page, 
								  @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getAll(page, size);
	}
	@RequestMapping(value = "/first-name/{firstName}", method = RequestMethod.GET)
	public Slice<Seller> getByFirstName(@PathVariable("firstName")String firstName,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getByFirstName(firstName, page, size);
	}
	@RequestMapping(value = "/last-name/{lastName}", method = RequestMethod.GET)
	public Slice<Seller> getByLastName(@PathVariable("lastName")String lastName,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getByFirstName(lastName, page, size);
	}
	@RequestMapping(value = "/gender/{gender}", method = RequestMethod.GET)
	public Slice<Seller> getByGender(@PathVariable("gender")String gender,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getByGender(gender, page, size);
	}
	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
	public Slice<Seller> getByCity(@PathVariable("city")String city,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getByCity(city, page, size);
	}
	@RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
	public Slice<Seller> getByState(@PathVariable("state")String state,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getByState(state, page, size);
	}
	@RequestMapping(value = "/pincode/{pincode}", method = RequestMethod.GET)
	public Slice<Seller> getByPincode(@PathVariable("pincode")long pincode,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getByPincode(pincode, page, size);
	}
	@RequestMapping(value= "/email/{email}", method = RequestMethod.GET)
	public Seller getByEmail(@PathVariable("email")String email)
	{
		return sellerService.getByEmail(email);
	}
	@RequestMapping(value = "/gov-id-type/{govIdType}", method = RequestMethod.GET)
	public Slice<Seller> getByGovIdType(@PathVariable("govIdType")String govIdType,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size)
	{
		return sellerService.getByGovIdType(govIdType, page, size);
	}
	@RequestMapping(value= "/gov-id/{govId}", method = RequestMethod.GET)
	public Seller getByGovId(@PathVariable("govId")String govId)
	{
		return sellerService.getByGovId(govId);
	}
}
