package com.example.shop.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.models.*;

@Repository
public interface PincodeRepository extends MongoRepository<Pincode, String> {
	
}
