package com.example.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.shop.models.*;
import com.example.shop.repository.PincodeRepository;

@Service
public class PincodeService {
	private PincodeRepository pincodeRepository;
	
	@Autowired
	public PincodeService(PincodeRepository pincodeRepository)
	{
		this.pincodeRepository = pincodeRepository;
	}
	
	public List<Pincode> getAll()
	{
		return pincodeRepository.findAll();
	}
	public Slice<Pincode> getAll(int page, int size)
	{
		Pageable pageable = PageRequest.of(page, size);
		Slice<Pincode> slice = this.pincodeRepository.findAll(pageable);
		return slice;
	}
}
