package com.example.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.shop.models.BuyOrder;
import com.example.shop.models.OrderStatus;
import com.example.shop.models.PaymentType;
import com.example.shop.repository.BuyOrderRepository;

@Service
public class BuyOrderService {
	private BuyOrderRepository buyOrderRepository;
	
	@Autowired
	public BuyOrderService(BuyOrderRepository buyOrderRepository)
	{
		this.buyOrderRepository = buyOrderRepository;
	}
	public Slice<BuyOrder> getAll(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findAll(pageable);
	}
	public Slice<BuyOrder> getByCustomerId(long customerId, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByCustomerId(customerId, pageable);
	}
	public Slice<BuyOrder> getBySellerId(long sellerId, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findBySellerId(sellerId, pageable);
	}
	public Slice<BuyOrder> getByProductId(long productId, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByProductId(productId, pageable);
	}
	public Slice<BuyOrder> getByPaymentType(String paymentType, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		try
		{
			PaymentType value = PaymentType.valueOf(paymentType.toUpperCase());
			return buyOrderRepository.findByPaymentType(value, pageable);
		}
		catch(Exception exception)
		{
			return buyOrderRepository.findByPaymentType(null, pageable);
		}
	}
	public Slice<BuyOrder> getByOrderStatus(String orderStatus, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		try
		{
			OrderStatus value = OrderStatus.valueOf(orderStatus.toUpperCase());
			return buyOrderRepository.findByOrderStatus(value, pageable);
		}
		catch(Exception exception)
		{
			return buyOrderRepository.findByOrderStatus(null, pageable);
		}
	}
	public Slice<BuyOrder> getByShippingCity(String shippingCity, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByShippingCityIgnoreCase(shippingCity, pageable);
	}
	public Slice<BuyOrder> getByShippingState(String shippingState, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByShippingStateIgnoreCase(shippingState, pageable);
	}
	public Slice<BuyOrder> getByShippingAddress(String shippingAddress, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByShippingAddressIgnoreCase(shippingAddress, pageable);
	}
	public Slice<BuyOrder> getByPincode(long pincode, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		return buyOrderRepository.findByPincode(pincode, pageable);
	}
}
