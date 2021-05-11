package com.example.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.models.BuyOrder;
import com.example.shop.models.OrderStatus;
import com.example.shop.models.PaymentType;

@Repository
public interface BuyOrderRepository extends MongoRepository<BuyOrder, Long>{
	Page<BuyOrder> findByCustomerId(long customerId, Pageable pageable);
	Page<BuyOrder> findByProductId(long productId, Pageable pageable);
	Page<BuyOrder> findBySellerId(long sellerId, Pageable pageable);
	Page<BuyOrder> findByPaymentType(PaymentType paymentType, Pageable pageable);
	Page<BuyOrder> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);
	Page<BuyOrder> findByShippingCityIgnoreCase(String city, Pageable pageable);
	Page<BuyOrder> findByShippingStateIgnoreCase(String state, Pageable pageable);
	Page<BuyOrder> findByShippingAddressIgnoreCase(String addresss, Pageable pageable);
	Page<BuyOrder> findByPincode(long pincode, Pageable pageable);
	
}
