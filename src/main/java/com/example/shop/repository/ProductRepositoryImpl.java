package com.example.shop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.shop.models.Product;
@Repository
public class ProductRepositoryImpl implements CustomProductRepository{
	@Autowired MongoTemplate mongoTemplate;
	
	@Override
	public long getCount() {
		return 10000;
	}

	@Override
	public boolean brandExists(String brand) {
		Query query =new Query();
		query.addCriteria(Criteria.where("brand").is(brand));
		return mongoTemplate.exists(query, Product.class, "product");
	}
	

	

	
}
