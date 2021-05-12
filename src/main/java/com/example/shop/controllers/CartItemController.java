package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.CartItem;
import com.example.shop.services.CartItemService;

@RestController
@CrossOrigin
@RequestMapping("/cart-item")
public class CartItemController {
	private CartItemService cartItemService;
	
	@Autowired
	public CartItemController(CartItemService cartItemService) {
		super();
		this.cartItemService = cartItemService;
	}
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Page<CartItem> getAll(@RequestParam(defaultValue = "0")int page, 
    							@RequestParam(defaultValue ="20")int size)
    {
    	return cartItemService.getAll(page, size);
    }
    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public Page<CartItem> getAll(@PathVariable("customerId")long customerId,
    							@RequestParam(defaultValue = "0")int page, 
    							@RequestParam(defaultValue ="20")int size)
    {
    	return cartItemService.getByCustomerId(customerId, page, size);
    }
	
}
