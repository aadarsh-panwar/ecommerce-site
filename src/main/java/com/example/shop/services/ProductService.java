package com.example.shop.services;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.models.Category;
import com.example.shop.models.Product;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.SellerRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {
	@Autowired
	MongoTemplate mongoTemplate;
	private ProductRepository productRepository;
	
	@Autowired
	private SellerRepository sellerRepository;
	private long lastGeneratedId = -1;
	
	@Autowired 
	public ProductService(ProductRepository productRepository) {
		this.productRepository =productRepository;
	}
	
	public Page<Product> getAll(int page, int size) {
		Pageable pageble = PageRequest.of(page, size);
		return productRepository.findAll(pageble);
	}
	public Page<Product> getByCategory(String category, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		try {
		Category value = Category.valueOf(category.toUpperCase());
		return productRepository.findByCategory(value,pageable);
		}
		catch(Exception exception) {
			return productRepository.findByCategory(null, pageable);
		}
		
	}
	public Product getById(long id) {
		Optional product = this.productRepository.findById(id);
		if(product.isEmpty()) {
			return null;
			}
		else {
			return (Product)product.get();
		}
	}

	public Page<Product> getAll(int page, int size, String sortOrder) {
		if(sortOrder.equalsIgnoreCase("none")) {
			return this.getAll(page, size);
		}
		else if(sortOrder.equalsIgnoreCase("ASC")) {
			Pageable pageable = PageRequest.of(page, size,Sort.Direction.ASC, "attributes.price");
			return this.productRepository.findAll(pageable);
		}
		else {
			Pageable pageable = PageRequest.of(page, size, Direction.DESC,"attributes.price");
			return this.productRepository.findAll(pageable);
		}
	}

	public void uploadProductList(long sellerId, MultipartFile file) throws JsonParseException, JsonMappingException, IOException {
		if(!file.getContentType().equalsIgnoreCase("application/json")) {
			throw new IllegalArgumentException("Invalid format of file");
		}
		ObjectMapper mapper = new ObjectMapper();
		
		List<Product> products = mapper.readValue(file.getInputStream(), new TypeReference<List<Product>>(){});
		if(this.lastGeneratedId ==-1) {
			lastGeneratedId = this.productRepository.findTop1ByOrderByIdDesc().getId();
		}
		for(Product p: products) {	
			p.setId(++this.lastGeneratedId);
			p.setSellerId(sellerId);
		}
		List<Product> savedProducts = this.productRepository.saveAll(products);
//		System.out.println("checking saved products");
//		System.out.println("count:" +savedProducts.size());
//		for(Product p: savedProducts)
//		{
//			System.out.println(p);
//		}
	}
	public void addImage(long sellerId,long productId, String imageUrl) {
		Optional<Product> product = this.productRepository.findById(productId);
		if(product.isEmpty()) {
			throw new IllegalArgumentException("Product doesn't exists:"+ productId);
		}
		Product pro = product.get();
		String oldUrl = (String)pro.getAttributes().get("image_url");
		String newUrl = oldUrl.concat("|").concat(imageUrl);
		pro.getAttributes().put("image_url", newUrl);
		this.productRepository.save(pro);
	}
	
	public Page<Product> getBySellerId(long sellerId, int page, int size,String sortOrder) {
		boolean sellerExists = this.sellerRepository.existsById(sellerId);
		if(!sellerExists) {
			throw new IllegalArgumentException("Seller doesn't exists:"+ sellerId);
		}
		Page<Product> products = null;
		if(sortOrder.equalsIgnoreCase("title")) {
			Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "title");
			products = this.productRepository.findBySellerId(sellerId, pageable);
		}
		Pageable pageable = PageRequest.of(page, size);
		products = this.productRepository.findBySellerId(sellerId, pageable);
		return products;
	}
	
	
	public Page<Product> searchProduct(String word,int page, int size)
	{
		TextCriteria criteria = TextCriteria.forDefaultLanguage()
				  .matchingAny(word);
		long total = this.mongoTemplate.count(TextQuery.queryText(criteria), Product.class);
		Pageable pageable = PageRequest.of(page, size);
		Query query = TextQuery.queryText(criteria)
				  .sortByScore()
				  .with(pageable);
		List<Product> productList = mongoTemplate.find(query, Product.class);
		Page<Product> productPage = new PageImpl<>(productList, pageable, total);
		return productPage;	
	}
}



























