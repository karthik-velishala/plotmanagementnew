package com.fissionlabs.model;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "owner")
public class Owner {
	@Id
	private String id;

	public Owner(String id, String firstName, String lastName,
			String country, String dob, String gender, String contactNo,
			String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.dob = dob;
		this.gender = gender;
		this.contactNo = contactNo;
		this.email = email;
	}

	public Owner() {
	}

	private String firstName;
	private String lastName;
@NotNull
	private String country;
	private String dob;
	private String gender;
	private String contactNo;
	private String email;

	//@OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
	@DBRef
	@JsonIgnore
	private Set<Property> property;

	public Set<Property> getProperty() {
		return property;
	}

	public void setProperty(Set<Property> property) {
		this.property = property;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	public Owner(String id)
	{
		this.id=id;
	}
}
