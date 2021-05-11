package com.example.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.models.GovIdType;
import com.example.shop.models.Seller;

@Repository
public interface SellerRepository extends MongoRepository<Seller, Long>{

	Page<Seller> findByFirstNameIgnoreCase(String firstName, Pageable pageable);
	Page<Seller> findByLastNameIgnoreCase(String lastName,Pageable pageable);
	Page<Seller> findByGenderIgnoreCase(String gender,Pageable pageable);
	Seller findByEmailIgnoreCase(String email);
	Page<Seller> findByCityIgnoreCase(String city,Pageable pageable);
	Page<Seller> findByStateIgnoreCase(String state,Pageable pageable);
	Page<Seller> findByPincode(long pincode,Pageable pageable);
	Page<Seller> findByGovIdType(GovIdType govIdType, Pageable pageable );
	Seller findByGovId(String govId);
}
