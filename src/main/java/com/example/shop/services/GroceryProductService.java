package com.example.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.shop.models.GroceryProduct;
import com.example.shop.repository.GroceryProductRepository;

@Service
public class GroceryProductService {
	private GroceryProductRepository groceryProductRepository;
	
	@Autowired
	public GroceryProductService(GroceryProductRepository groceryProductRepository)
	{
		this.groceryProductRepository = groceryProductRepository;
	}
	
	public Slice<GroceryProduct> getAll(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<GroceryProduct> slice = groceryProductRepository.findAll(pageable);
		return slice;
	}
	
}
