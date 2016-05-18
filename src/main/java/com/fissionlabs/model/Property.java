package com.fissionlabs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "property")
public class Property {
	@Id
	private String propertyId;

	private String propertyType;
	private int noOfRooms;
	private int area;
	private int price;
	private String propertyName;
	private String city;
	private String hno;
	private String roadno;
	private String landmark;
	private String status;

	public String getHno() {
		return hno;
	}

	public void setHno(String hno) {
		this.hno = hno;
	}

	public String getRoadno() {
		return roadno;
	}

	public void setRoadno(String roadno) {
		this.roadno = roadno;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	
	@DBRef
	// @JsonIgnore
	private Owner owner;

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertysId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Property(String propertyId, String propertyType, int noOfRooms,
			int area, int price, String propertyName, String city, Owner owner,
			String hno, String roadno, String landmark, String status) {
		super();
		this.propertyId = propertyId;
		this.propertyType = propertyType;
		this.noOfRooms = noOfRooms;
		this.area = area;
		this.price = price;
		this.propertyName = propertyName;
		this.city = city;
		this.owner = owner;
		this.hno = hno;
		this.roadno = roadno;
		this.landmark = landmark;
		this.status = status;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Property() {
	}

	public Property(String propertyID) {
		this.propertyId = propertyID;
	}

}
