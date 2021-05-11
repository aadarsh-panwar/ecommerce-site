package com.example.shop.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("seller")
public class Seller implements java.io.Serializable, Comparable<Seller>{

	@Id
	private long id;
	@Field("first_name")
	private String firstName;
	@Field("last_name")
	private String lastName;
	private String email;
	private String gender;
	@Field("date_of_birth")
	private LocalDate dateOfBirth;
	@Field("contact_number")
	private String contactNumber;
	private String city;
	private String state;
	private long pincode;
	private String address;
	@Field("gov_id_type")
	private GovIdType govIdType;
	@Field("gov_id")
	private String govId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public GovIdType getGovIdType() {
		return govIdType;
	}
	public void setGovIdType(GovIdType govIdType) {
		this.govIdType = govIdType;
	}
	public String getGovId() {
		return govId;
	}
	public void setGovId(String govId) {
		this.govId = govId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public int compareTo(Seller o) {
		return (int) (this.id - o.id);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((contactNumber == null) ? 0 : contactNumber.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((govId == null) ? 0 : govId.hashCode());
		result = prime * result + ((govIdType == null) ? 0 : govIdType.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (pincode ^ (pincode >>> 32));
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(!(obj instanceof Seller))
			return false;
		Seller other = (Seller)obj;
		return this.id == other.id;
	}
	@Override
	public String toString() {
		return "Seller [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + ", contactNumber=" + contactNumber + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", govIdType=" + govIdType + ", govId=" + govId + "]";
	}
	
}
