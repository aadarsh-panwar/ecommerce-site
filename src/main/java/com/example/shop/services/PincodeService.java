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
	public PincodeService(PincodeRepository pincodeRepository) {
		this.pincodeRepository = pincodeRepository;
	}
	public Slice<Pincode> getAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Pincode> slice = this.pincodeRepository.findAll(pageable);
		return slice;
	}
	public Slice<Pincode> getByDivisionName(String divisionName, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Pincode> slice = this.pincodeRepository.findByDivisionNameIgnoreCase(divisionName, pageable);
		return slice;
	}
	public List<Pincode> getByOfficeName(String officeName) {
		List<Pincode> pincodes = this.pincodeRepository.findByOfficeNameIgnoreCase(officeName);
		return pincodes;
	}
	public List<Pincode> getByCode(int code) {
		List<Pincode> pincodes = this.pincodeRepository.findByCode(code);
		return pincodes;
	}
	public Slice<Pincode> getByDistrict(String district, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Slice<Pincode> slice = this.pincodeRepository.findByDistrictIgnoreCase(district, pageable);
		return slice;
	}
	public Slice<Pincode> getByState(String state, int page, int size) {
		Pageable pageable = PageRequest.of(page,  size);
		Slice<Pincode> slice = this.pincodeRepository.findByStateIgnoreCase(state, pageable);
		return slice;
	}
}
