package com.example.shop.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.shop.models.Customer;
import com.example.shop.models.Review;
import com.example.shop.models.request.ReviewRequest;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.ReviewRepository;
import com.example.shop.util.JwtUtil;

@Service
public class ReviewService {
	
	private ReviewRepository reviewRepository;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired 
	CustomerRepository customerRepository; 
	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	public Page<Review> getAll(long productId, int page, int size, String sortOrder) {
		if(sortOrder.equalsIgnoreCase("none")) {
			Pageable pageable = PageRequest.of(page, size);
			return reviewRepository.findByProductId(productId, pageable);
		}
		else if(sortOrder.equalsIgnoreCase("POSITIVE_FIRST")) {
			Pageable pageable = PageRequest.of(page, size,Sort.Direction.DESC, "rating");
			return this.reviewRepository.findByProductId(productId, pageable);
		}
		else if(sortOrder.equalsIgnoreCase("NEGATIVE_FIRST")) {
			Pageable pageable = PageRequest.of(page, size, Direction.ASC,"rating");
			return this.reviewRepository.findByProductId(productId, pageable);
		}
		else  {
			Pageable pageable =  PageRequest.of(page, size, Direction.DESC, "date");
			return this.reviewRepository.findByProductId(productId, pageable);
		}
	}
	public void submitReview(ReviewRequest request) {
		String username = jwtUtil.extractUsername(request.getToken());
		Customer customer = this.customerRepository.findByEmailIgnoreCase(username);
		if(customer == null) {
			throw new IllegalStateException("Invalid token, log in again then try");
		}
		Review review = new Review();
		review.setCustomerId(customer.getId());
		review.setFirstName(customer.getFirstName());
		review.setLastName(customer.getLastName());
		review.setText(request.getText());
		review.setSummary(request.getSummary());
		review.setDate(LocalDate.now());
		review.setProductId(request.getProductId());
		review.setRating(request.getRating());
		this.reviewRepository.save(review);
	
	}

	public Map<String, Integer> getSummary(long productId) {
		Map<String, Integer> ratings =new HashMap();
		ratings.put("1", 0);
		ratings.put("2", 0);
		ratings.put("3", 0);
		ratings.put("4", 0);
		ratings.put("5", 0);
		
		List<Review> reviews = this.reviewRepository.findByProductId(productId);
		System.out.println(reviews.size());
		for(Review review : reviews) {
			int prevRating = ratings.get(String.valueOf(review.getRating()));
			ratings.put(String.valueOf(review.getRating()),++prevRating );
		}
		return ratings;
	}
}
