package com.example.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.example.shop.models.GovIdType;
import com.example.shop.models.Seller;
import com.example.shop.repository.SellerRepository;

@Service
public class SellerService {
	private SellerRepository sellerRepository;
	
	@Autowired
	public SellerService(SellerRepository sellerRepository)
	{
		this.sellerRepository = sellerRepository;
	}
	
	public Slice<Seller> getAll(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Seller> slice = sellerRepository.findAll(pageable);
		return slice;
	}

	public Slice<Seller> getByFirstName(String firstName, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Seller> slice =sellerRepository.findByFirstNameIgnoreCase(firstName, pageable); 
		return slice;
	}

	public Slice<Seller> getByLastName(String lastName, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Seller> slice =sellerRepository.findByLastNameIgnoreCase(lastName, pageable); 
		return slice;
	}

	public Slice<Seller> getByGender(String gender, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Seller> slice =sellerRepository.findByGenderIgnoreCase(gender, pageable); 
		return slice;
	}
	

	public Seller getByEmail(String email)
	{
		Seller seller =sellerRepository.findByEmailIgnoreCase(email); 
		return seller;
	}

	public Slice<Seller> getByCity(String city, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Seller> slice =sellerRepository.findByCityIgnoreCase(city, pageable); 
		return slice;
	}

	public Slice<Seller> getByState(String state, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Seller> slice =sellerRepository.findByStateIgnoreCase(state, pageable); 
		return slice;
	}

	public Slice<Seller> getByPincode(long pincode, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Seller> slice =sellerRepository.findByPincode(pincode, pageable); 
		return slice;
	}
	public Slice<Seller> getByGovIdType(String govIdType, int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		try
		{
			GovIdType value = GovIdType.valueOf(govIdType.toUpperCase());
		Slice<Seller> slice = sellerRepository.findByGovIdType(value, pageable);
		return slice;
		}
		catch(Exception exception)
		{
			return sellerRepository.findByGovIdType(null, pageable);
		}
	}
	public Seller getByGovId(String govId)
	{
		return sellerRepository.findByGovId(govId);
	}
	
}
