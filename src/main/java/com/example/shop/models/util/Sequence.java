package com.example.shop.models.util;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("sequence")
public class Sequence {

	@Field("last_id")
	private Map<String, Long> lastId;

	public Map<String, Long> getLastId() {
		return lastId;
	}

	public void setLastId(Map<String, Long> lastId) {
		this.lastId = lastId;
	}
	
}
