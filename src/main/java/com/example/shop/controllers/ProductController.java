package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Page<Product> getAll(@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue ="20")int size)
	{
		return productService.getAll(page, size);
	}
	@RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
	public Page<Product> getByCategory(@PathVariable("category")String category,
								@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue ="20")int size)
	{
		return productService.getByCategory(category, page, size);
	}
}
