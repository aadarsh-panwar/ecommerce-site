package com.example.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.shop.models.CartItem;

public interface CartItemRepository extends MongoRepository<CartItem, String>{
	Page<CartItem> findByCustomerId(long customerId, Pageable pageable);
}
