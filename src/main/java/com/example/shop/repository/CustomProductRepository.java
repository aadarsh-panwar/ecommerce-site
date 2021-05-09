package com.example.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shop.models.Product;


public interface CustomProductRepository {
	long getCount();
	boolean brandExists(String brand);
}
