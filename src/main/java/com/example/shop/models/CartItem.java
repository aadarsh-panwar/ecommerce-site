package com.example.shop.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("cart")
public class CartItem {
	@Id
	private String id;
	@Field("customer_id")
	private long customerId;
	private long productId;
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
	public long getProductsId() {
		return productId;
	}
	public void setProductsId(long productId) {
		this.productId = productId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (customerId ^ (customerId >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (productId ^ (productId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if(!(obj instanceof CartItem))
			return false;
		CartItem other = (CartItem)obj;
		return this.id.equals(other.id);
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", customerId=" + customerId + ", productId=" + productId + "]";
	}
	
}
