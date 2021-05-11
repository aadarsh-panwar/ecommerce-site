package com.example.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import com.example.shop.models.Customer;
import com.example.shop.repository.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository)
	{
		this.customerRepository = customerRepository;
	}
	
	public Slice<Customer> getAll(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Customer> slice = customerRepository.findAll(pageable);
		return slice;
	}

	public Slice<Customer> getByFirstName(String firstName, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Customer> slice =customerRepository.findByFirstNameIgnoreCase(firstName, pageable); 
		return slice;
	}

	public Slice<Customer> getByLastName(String lastName, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Customer> slice =customerRepository.findByLastNameIgnoreCase(lastName, pageable); 
		return slice;
	}

	public Slice<Customer> getByGender(String gender, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Customer> slice =customerRepository.findByGenderIgnoreCase(gender, pageable); 
		return slice;
	}
	

	public Customer getByEmail(String email)
	{
		Customer customer =customerRepository.findByEmailIgnoreCase(email); 
		return customer;
	}

	public Slice<Customer> getByCity(String city, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Customer> slice =customerRepository.findByCityIgnoreCase(city, pageable); 
		return slice;
	}

	public Slice<Customer> getByState(String state, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Customer> slice =customerRepository.findByStateIgnoreCase(state, pageable); 
		return slice;
	}

	public Slice<Customer> getByPincode(long pincode, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Customer> slice =customerRepository.findByPincode(pincode, pageable); 
		return slice;
	}
}
