package com.fissionlabs.model;

import org.springframework.data.annotation.Id;

public class Location {
	@Id
	private String id;
	private Float latitude;
	private Float longitude;
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Location(Float latitude, Float longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
