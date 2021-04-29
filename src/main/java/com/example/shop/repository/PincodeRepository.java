package com.example.shop.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.models.Pincode;

@Repository
public interface PincodeRepository extends MongoRepository<Pincode, String> {
	Page<Pincode> findByDivisionNameIgnoreCase(String divisonName, Pageable pageable);
	List<Pincode> findByOfficeNameIgnoreCase(String officeName);
	List<Pincode> findByCode(int code);
	Page<Pincode> findByDistrictIgnoreCase(String district, Pageable pageable);
	Page<Pincode> findByStateIgnoreCase(String state, Pageable pageable);
	List<Pincode> findByDistrict(String district);
}
