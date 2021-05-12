package com.example.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.shop.models.CartItem;
import com.example.shop.repository.CartItemRepository;

public class CartItemService {
	private CartItemRepository cartItemRepository;
	
	@Autowired
	public CartItemService(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}
	
	public Page<CartItem> getAll(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return cartItemRepository.findAll(pageable);
	}

	public Page<CartItem> getByCustomerId(long customerId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return cartItemRepository.findByCustomerId(customerId, pageable);
	}
	
	
}
