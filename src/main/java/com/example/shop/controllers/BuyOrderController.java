package com.example.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.BuyOrder;
import com.example.shop.services.BuyOrderService;

@RestController
@CrossOrigin
@RequestMapping("/buy-order")
public class BuyOrderController {
	private BuyOrderService buyOrderService;
	
	@Autowired
	public BuyOrderController(BuyOrderService buyOrderService)
	{
		this.buyOrderService = buyOrderService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Slice<BuyOrder> getAll(@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getAll(page, size);
	}
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByCustomerId(@PathVariable("customerId")long customerId,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getByCustomerId(customerId, page, size);
	}
	@RequestMapping(value = "/seller/{sellerId}", method = RequestMethod.GET)
	public Slice<BuyOrder> getSellerId(@PathVariable("sellerId")long sellerId,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getBySellerId(sellerId, page, size);
	}
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByProductId(@PathVariable("productId")long productId,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getByProductId(productId, page, size);
	}
	@RequestMapping(value = "/shipping-city/{shippingCity}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByShippingCity(@PathVariable("shippingCity")String shippingCity,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getByShippingCity(shippingCity, page, size);
	}
	@RequestMapping(value = "/shipping-state/{shippingState}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByShippingState(@PathVariable("shippingState")String shippingState,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getByShippingState(shippingState, page, size);
	}
	@RequestMapping(value = "/pincode/{pincode}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByPincode(@PathVariable("pincode")long pincode,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getByPincode(pincode, page, size);
	}
	@RequestMapping(value = "/payment-type/{paymentType}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByPaymentType(@PathVariable("paymentType")String paymentType,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getByPaymentType(paymentType, page, size);
	}
	@RequestMapping(value = "/order-status/{orderStatus}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByOrderStatus(@PathVariable("orderStatus")String orderStatus,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size)
	{
		return buyOrderService.getByOrderStatus(orderStatus, page, size);
	}
	
	
}
