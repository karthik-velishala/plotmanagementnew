package com.fissionlabs.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class PropertyLocation {
	/* PropertyLocation is creating property by latitude and longitude */

	@Id
	private String id;

	private double[] location;

	private List<String> tags;
	private String source;
	private Date timestamp;
	private Object meta;
	private Double accuracy;

	public Object getMeta() {
		return meta;
	}

	public void setMeta(Object meta) {
		this.meta = meta;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

	public double[] getLocation() {
		return location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PropertyLocation(String id, double[] location, List<String> tags,
			String source, Date timestamp) {
		super();
		this.id = id;
		this.location = location;
		this.tags = tags;
		this.source = source;
		this.timestamp = timestamp;
	}

	public void setTimestamp(Date timestamp2) {
		this.timestamp = timestamp2;
	}

	public PropertyLocation(String id) {
		this.id = id;
	}

	public PropertyLocation() {
		// TODO Auto-generated constructor stub
	}
}
