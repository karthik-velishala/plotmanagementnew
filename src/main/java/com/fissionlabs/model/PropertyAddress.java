package com.fissionlabs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="property_address")
public class PropertyAddress {
	@Id
	
	@Field(value="property_address_id")
	private Integer propertyAddressId;
	private String city;
	private String areaName;
	private String Country;
	private Integer pincode;
	private String streetName;
	//@OneToOne
	//@JoinColumn(name="property_id")
	private Property property;;
	
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public Integer getPropertyAddressId() {
		return propertyAddressId;
	}
	public void setPropertyAddressId(Integer propertyAddressId) {
		this.propertyAddressId = propertyAddressId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	
	
	

}
