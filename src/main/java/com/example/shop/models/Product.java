package com.example.shop.models;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document("product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	@Id
	private long id;
	@TextIndexed(weight = 5)
	private String title;
	private String brand;
	private String manufacturer;
	private int mrp;
	private Map<String, Object> attributes = new HashMap<>();
	private Category category;
	@Field("seller_id")
	private long sellerId;
	@TextScore
	private Float score;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getMrp() {
		return mrp;
	}
	public void setMrp(int mrp) {
		this.mrp = mrp;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setAttribute(String key, Object value)
	{
		this.attributes.put(key, value);
	}
	public Object getAttribute(String key)
	{
		return this.attributes.get("key");
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public long getSellerId() {
		return sellerId;
	}
	public void setSellerId(long sellerId) {
		this.sellerId = sellerId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + mrp;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Product))
		return false;
		Product other =(Product)obj;
		return this.id == other.id;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", brand=" + brand + ", manufacturer=" + manufacturer
				+ ", mrp=" + mrp + ", attributes=" + attributes + ", category=" + category + "]";
	}
	
}
