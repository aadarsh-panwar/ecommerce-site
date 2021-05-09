package com.example.shop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;
	
	@Autowired 
	public ProductService(ProductRepository productRepository)
	{
		this.productRepository =productRepository;
	}
	
	public Page<Product> getAll(int page, int size)
	{
		Pageable pageble = PageRequest.of(page, size);
		return productRepository.findAll(pageble);
	}
	public Page<Product> getByCategory(String category, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Catogory value = Category.valueOf(category);
		return productRepository.findByCategoryIgnoreCase(category.toUpperCase(),pageable);
	}
}
