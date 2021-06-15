package com.example.shop.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.models.Review;
import com.example.shop.models.request.ReviewRequest;
import com.example.shop.services.ReviewService;

@RestController
@CrossOrigin
@RequestMapping("/review")
public class ReviewController {
	private ReviewService reviewService;
	
	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@RequestMapping(value = "/product/{productId}",method = RequestMethod.GET)
	public Page<Review> getAll( @PathVariable("productId")long productId,
								@RequestParam(defaultValue= "0")int page,
								@RequestParam(defaultValue = "20")int size,
								@RequestParam(defaultValue ="NONE")String sortOrder) {
		return this.reviewService.getAll(productId, page, size, sortOrder);
	}
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public ResponseEntity<?> submitReview(@RequestBody ReviewRequest request) {
		try {
			this.reviewService.submitReview(request);
			return ResponseEntity.ok().build();
		}
		catch(Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}
	@RequestMapping(value = "/summary/product/{productId}", method = RequestMethod.GET)
	public ResponseEntity<?> getSummary(@PathVariable("productId")long productId) {
		try {
			Map<String, Integer> ratings = this.reviewService.getSummary(productId);
			return ResponseEntity.ok(ratings);
		}
		catch(Exception exception) {
			return ResponseEntity.badRequest().build();
		}
	}
}

