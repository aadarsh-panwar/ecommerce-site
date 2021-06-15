package com.example.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.shop.models.CartItem;
import com.example.shop.models.Customer;
import com.example.shop.models.Product;
import com.example.shop.repository.CartItemRepository;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.util.JwtUtil;
@Service
public class CartItemService {
	private CartItemRepository cartItemRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	public CartItemService(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}
	
	public Page<CartItem> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return cartItemRepository.findAll(pageable);
	}

	public void add(long customerId, long productId) {
		CartItem cartItem = new CartItem();
		cartItem.setId(UUID.randomUUID().toString());
		cartItem.setCustomerId(customerId);
		cartItem.setProductsId(productId);
		this.cartItemRepository.save(cartItem);
	}

	public List<Product> getByCustomerId(String email) {
		Customer customer = this.customerRepository.findByEmailIgnoreCase(email);
		if(customer == null) {
			return new ArrayList<Product>();
		}
		long customerId = customer.getId();
		List<CartItem> items = this.cartItemRepository.findByCustomerId(customerId);
		List<Product> products = new ArrayList<>();
		for(CartItem item: items) {
			Optional<Product> product = this.productRepository.findById(item.getProductsId());
			if(product.isPresent()) {
				products.add(product.get());
				
			}
		}
		return products;
		
	}

	public void add(long productId, String token) {
		String username = jwtUtil.extractUsername(token);
		Customer customer = customerRepository.findByEmailIgnoreCase(username);
		CartItem cartItem = new CartItem();
		cartItem.setProductsId(productId);
		cartItem.setCustomerId(customer.getId());
		this.cartItemRepository.save(cartItem);
	}

	public int getItemCount(long id) {
		return this.cartItemRepository.findByCustomerId(id).size();
	}
	
	
}
