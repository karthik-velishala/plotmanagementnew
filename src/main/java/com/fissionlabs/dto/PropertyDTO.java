package com.fissionlabs.dto;

import com.fissionlabs.model.Owner;

public class PropertyDTO {

	private String propertyType;
	private int noOfRooms;
	private int area;
	private int price;
	private String propertyName;
	private String city;
	private Owner owner;
	private String hno;
	private String roadno;
	private String landmark;
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
	/*	private PropertyAddress propertyAddress;
	
	
	public PropertyAddress getPropertyAddress() {
		return propertyAddress;
	}
	public void setPropertyAddress(PropertyAddress propertyAddress) {
		this.propertyAddress = propertyAddress;
	}*/
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		this.owner = owner;
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
}
