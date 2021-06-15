package com.example.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;

import com.example.shop.models.Product;

@Component
public class SampleRunner implements CommandLineRunner{
	
	@Autowired
	MongoTemplate mongoTemplate;
	public void run(String... args) throws Exception {
			
		//Query query = new Query();
	//long total = this.mongoTemplate.count(query, Product.class);
	//System.out.println("total:"+ total);
//	for(Product p : products)
//	{
//		System.out.println(p);
//	}
	
	}
	
}
