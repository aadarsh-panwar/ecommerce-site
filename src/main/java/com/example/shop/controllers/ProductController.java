package com.example.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.models.Product;
import com.example.shop.models.request.ImageUrl;
import com.example.shop.services.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Page<Product> getAll(@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue ="20")int size,
								@RequestParam(defaultValue ="NONE")String sortOrder) {
		return productService.getAll(page, size, sortOrder);
	}
	@RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
	public Page<Product> getByCategory(@PathVariable("category")String category,
								@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue ="20")int size) {
		return productService.getByCategory(category, page, size);
	}
	@RequestMapping(value = "/product-id/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getById(@PathVariable("productId")long productId) {
		Product product = this.productService.getById(productId);
		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		else {
			return ResponseEntity.ok(product);
		}
	}
	@RequestMapping(value = "/product-list/{sellerId}", method = RequestMethod.POST)
	public ResponseEntity<?> uploadProductList(@PathVariable("sellerId")long sellerId,
											@RequestParam("file")MultipartFile file) {
		try {
		this.productService.uploadProductList(sellerId, file);
		return ResponseEntity.ok().build();
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	@RequestMapping(value = "/image/seller/{sellerId}/product/{productId}", method = RequestMethod.POST)
	public ResponseEntity<?> uploadImage(@PathVariable("sellerId")long sellerId,
									@PathVariable("productId")long productId,
											@RequestBody ImageUrl imageUrl) {
		try {
		this.productService.addImage(sellerId,productId, imageUrl.getUrl());
		return ResponseEntity.ok().build();
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	@RequestMapping(value = "/seller/{sellerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@PathVariable("sellerId")long sellerId,
								@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue ="20")int size,
								@RequestParam(defaultValue ="NONE")String sortOrder) {
		try {
			Page<Product> products = productService.getBySellerId(sellerId, page, size, sortOrder);
			return ResponseEntity.ok(products);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam("query")String word,
								@RequestParam(defaultValue = "0")int page, 
								@RequestParam(defaultValue ="20")int size) {
		try {
			Page<Product> products = this.productService.searchProduct(word, page, size);
			return ResponseEntity.ok(products);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

