package com.example.shop.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("buy_order")
public class BuyOrder {
	@Id
	private String id;
	@Field("customer_id")
	private long customerId;
	@Field("product_id")
	private long productId;
	@Field("seller_id")
	private long sellerId;
	@Field("order_date")
	private LocalDate orderDate;
	@Field("order_ship_date")
	private LocalDate OrderShipDate;
	@Field("payment_type")
	private PaymentType paymentType;
	@Field("order_status")
	private OrderStatus orderStatus;
	@Field("shipping_city")
	private String shippingCity;
	@Field("shipping_state")
	private String shippingState;
	private int pincode;
	@Field("shipping_address")
	private String shippingAddress;
	@Field("payment_status")
	private PaymentStatus paymentStatus;
	
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDate getOrderShipDate() {
		return OrderShipDate;
	}
	public void setOrderShipDate(LocalDate orderShipDate) {
		OrderShipDate = orderShipDate;
	}
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getShippingCity() {
		return shippingCity;
	}
	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}
	public String getShippingState() {
		return shippingState;
	}
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((OrderShipDate == null) ? 0 : OrderShipDate.hashCode());
		result = prime * result + (int) (customerId ^ (customerId >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((paymentStatus == null) ? 0 : paymentStatus.hashCode());
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + pincode;
		result = prime * result + (int) (productId ^ (productId >>> 32));
		result = prime * result + (int) (sellerId ^ (sellerId >>> 32));
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((shippingCity == null) ? 0 : shippingCity.hashCode());
		result = prime * result + ((shippingState == null) ? 0 : shippingState.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(!(obj instanceof BuyOrder))
			return false;
		BuyOrder other = (BuyOrder) obj;
		return this.id.equalsIgnoreCase(other.id);
	}
	@Override
	public String toString() {
		return "BuyOrder [id=" + id + ", customerId=" + customerId + ", productId=" + productId + ", sellerId="
				+ sellerId + ", orderDate=" + orderDate + ", OrderShipDate=" + OrderShipDate + ", paymentType="
				+ paymentType + ", orderStatus=" + orderStatus + ", shippingCity=" + shippingCity + ", shippingState="
				+ shippingState + ", pincode=" + pincode + ", shippingAddress=" + shippingAddress + "]";
	}
	
	
	
}
