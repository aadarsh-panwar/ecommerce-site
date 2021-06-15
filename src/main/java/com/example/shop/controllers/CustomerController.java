package com.example.shop.controllers;

import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.Customer;
import com.example.shop.services.CustomerService;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(value = "/all", method =RequestMethod.GET)
	public Slice<Customer> getAll(@RequestParam(defaultValue = "0")int page, 
								  @RequestParam(defaultValue = "20")int size) {
		return customerService.getAll(page, size);
	}
	@RequestMapping(value = "/first-name/{firstName}", method = RequestMethod.GET)
	public Slice<Customer> getByFirstName(@PathVariable("firstName")String firstName,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size) {
		return customerService.getByFirstName(firstName, page, size);
	}
	@RequestMapping(value = "/last-name/{lastName}", method = RequestMethod.GET)
	public Slice<Customer> getByLastName(@PathVariable("lastName")String lastName,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size) {
		return customerService.getByFirstName(lastName, page, size);
	}
	@RequestMapping(value = "/gender/{gender}", method = RequestMethod.GET)
	public Slice<Customer> getByGender(@PathVariable("gender")String gender,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size) {
		return customerService.getByGender(gender, page, size);
	}
	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
	public Slice<Customer> getByCity(@PathVariable("city")String city,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size) {
		return customerService.getByCity(city, page, size);
	}
	@RequestMapping(value = "/state/{state}", method = RequestMethod.GET)
	public Slice<Customer> getByState(@PathVariable("state")String state,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size) {
		return customerService.getByState(state, page, size);
	}
	@RequestMapping(value = "/pincode/{pincode}", method = RequestMethod.GET)
	public Slice<Customer> getByPincode(@PathVariable("pincode")long pincode,
										  @RequestParam(defaultValue = "0")int page,
									      @RequestParam(defaultValue = "20")int size) {
		return customerService.getByPincode(pincode, page, size);
	}
	@RequestMapping(value= "/email/{email}", method = RequestMethod.GET)
	public Customer getByEmail(@PathVariable("email")String email) {
		return customerService.getByEmail(email);
	}
}
