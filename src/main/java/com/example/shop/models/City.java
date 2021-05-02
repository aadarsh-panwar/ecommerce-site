package com.example.shop.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("city")
public class City implements java.io.Serializable, Comparable<City>{
	
	private String name;
	private String state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public int compareTo(City o) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object other) {
		if(other == this)
			return true;
		if(other == null)
			return false;
		if(!(other instanceof City))
			return false;
		City city = (City)other;
		return this.name.equalsIgnoreCase(city.name) &&
				this.state.equalsIgnoreCase(city.state);
	}
	@Override
	public String toString() {
		return "City [name=" + name + ", state=" + state + "]";
	}
	
	
}
