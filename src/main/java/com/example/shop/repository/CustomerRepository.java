package com.example.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long>{
	Page<Customer> findByFirstNameIgnoreCase(String firstName, Pageable pageable);
	Page<Customer> findByLastNameIgnoreCase(String lastName,Pageable pageable);
	Page<Customer> findByGenderIgnoreCase(String gender,Pageable pageable);
	Customer findByEmailIgnoreCase(String email);
	Page<Customer> findByCityIgnoreCase(String city,Pageable pageable);
	Page<Customer> findByStateIgnoreCase(String state,Pageable pageable);
	Page<Customer> findByPincode(long pincode,Pageable pageable);
	
}
