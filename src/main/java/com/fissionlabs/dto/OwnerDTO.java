package com.fissionlabs.dto;

import java.util.Set;

import com.fissionlabs.model.Property;

public class OwnerDTO {
	private String firstName;
	private String lastName;
	private String country;
	private String dob;
	private String gender;
	private String contactNo;
	private String email;
	private Set<Property> property;
	public Set<Property> getProperty() {
		return property;
	}
	public void setProperty(Set<Property> property) {
		this.property = property;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
