package com.example.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shop.models.util.Sequence;

public interface SequenceRepository extends MongoRepository<Sequence,String>{
	
}
