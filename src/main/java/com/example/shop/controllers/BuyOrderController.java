package com.example.shop.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.BuyOrder;
import com.example.shop.models.request.RequestToken;
import com.example.shop.models.request.ResponseBuyOrderPage;
import com.example.shop.services.BuyOrderService;

@RestController
@CrossOrigin
@RequestMapping("/buy-order")
public class BuyOrderController {
	private BuyOrderService buyOrderService;
	
	@Autowired
	public BuyOrderController(BuyOrderService buyOrderService) {
		this.buyOrderService = buyOrderService;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Slice<BuyOrder> getAll(@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getAll(page, size);
	}
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByCustomerId(@PathVariable("customerId")long customerId,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getByCustomerId(customerId, page, size);
	}
	@RequestMapping(value = "/seller/{sellerId}", method = RequestMethod.GET)
	public Slice<BuyOrder> getSellerId(@PathVariable("sellerId")long sellerId,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getBySellerId(sellerId, page, size);
	}
	@RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByProductId(@PathVariable("productId")long productId,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getByProductId(productId, page, size);
	}
	@RequestMapping(value = "/shipping-city/{shippingCity}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByShippingCity(@PathVariable("shippingCity")String shippingCity,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getByShippingCity(shippingCity, page, size);
	}
	@RequestMapping(value = "/shipping-state/{shippingState}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByShippingState(@PathVariable("shippingState")String shippingState,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getByShippingState(shippingState, page, size);
	}
	@RequestMapping(value = "/pincode/{pincode}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByPincode(@PathVariable("pincode")long pincode,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getByPincode(pincode, page, size);
	}
	@RequestMapping(value = "/payment-type/{paymentType}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByPaymentType(@PathVariable("paymentType")String paymentType,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getByPaymentType(paymentType, page, size);
	}
	@RequestMapping(value = "/order-status/{orderStatus}", method = RequestMethod.GET)
	public Slice<BuyOrder> getByOrderStatus(@PathVariable("orderStatus")String orderStatus,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size) {
		return buyOrderService.getByOrderStatus(orderStatus, page, size);
	}
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> map) {
		try {
		String order = buyOrderService.createOrder(map);
		System.out.println("order created response sent");
		return ResponseEntity.ok(order);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	@RequestMapping(value = "/history", method = RequestMethod.POST)
	public ResponseEntity<?> getHistory(@RequestBody RequestToken request,
										@RequestParam(defaultValue = "0")int page,
										@RequestParam(defaultValue="20")int size) {
		try {
			Page<BuyOrder> orders = this.buyOrderService.getHistory(request, page, size);
			return ResponseEntity.ok(orders);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@RequestMapping(value = "/history/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<?> getByOrderId(@PathVariable("orderId")String orderId) {
		try {
			Optional<BuyOrder> order = this.buyOrderService.getByOrderId(orderId);
			if(order.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.ok(order.get());
		}
		catch(Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(value = "/seller-order/{sellerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllOrders(@PathVariable("sellerId")long sellerId, 
										@RequestParam(defaultValue= "0")int page,
										@RequestParam(defaultValue= "12")int size) {
		try {
			ResponseBuyOrderPage orders = this.buyOrderService.getAllOrder(sellerId, page, size);
			return ResponseEntity.ok(orders);
		}
		catch(Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@RequestMapping(value = "/revenue-yearly/{sellerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getYearlyRevenue(@PathVariable("sellerId")long sellerId, 
										@RequestParam(defaultValue= "2000")int startYear,
										@RequestParam(defaultValue= "2000")int endYear) {
		try {
			Map<Integer, Long> revenue = this.buyOrderService.getYearlyRevenue(sellerId, startYear, endYear);
			return ResponseEntity.ok(revenue);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@RequestMapping(value = "/revenue-monthly/{sellerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getMonthyRevenue(@PathVariable("sellerId")long sellerId, 
										@RequestParam(defaultValue= "2000")int year,
										@RequestParam(defaultValue= "JANUARY")String startMonth,
										@RequestParam(defaultValue= "DECEMBER")String endMonth) {
		try {
			Map<String, Long> revenue = this.buyOrderService.getMonthlyRevenue(sellerId, year,startMonth, endMonth);
			return ResponseEntity.ok(revenue);
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
