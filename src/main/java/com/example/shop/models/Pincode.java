package com.example.shop.models;

import java.util.Comparator;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("pincode")
public class Pincode implements java.io.Serializable, Comparable<Pincode>{
	
	@Field("division_name")
	private String divisionName;
	@Field("office_name")
	private String officeName;
	private String district;
	private String state;
	private int code;
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public int compareTo(Pincode o1) {
		return this.code - o1.code;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((district == null) ? 0 : district.hashCode());
		result = prime * result + ((divisionName == null) ? 0 : divisionName.hashCode());
		result = prime * result + ((officeName == null) ? 0 : officeName.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object other) {
		if(other ==null)
			return false;
		if(this == other)
			return true;
		if(!(other instanceof Pincode))
			return false;
		Pincode pincode = (Pincode)other;
		return this.code == pincode.code &&
				this.divisionName.equalsIgnoreCase(pincode.divisionName) &&
				this.district.equalsIgnoreCase(pincode.district) && 
				this.officeName.equalsIgnoreCase(pincode.officeName) &&
				this.state.equalsIgnoreCase(pincode.state);
	}
	@Override
	public String toString() {
		return "Pincode [divisionName=" + divisionName + ", officeName=" + officeName + ", district=" + district
				+ ", state=" + state + ", code=" + code + "]";
	}

}
