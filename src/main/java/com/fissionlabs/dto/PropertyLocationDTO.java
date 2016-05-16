package com.fissionlabs.dto;

import java.security.Timestamp;
import java.util.List;

public class PropertyLocationDTO {

private double[] location;

	

	public double[] getLocation() {
	return location;
}

public void setLocation(double[] location) {
	this.location = location;
}

	private List<String> tags;
	private String source;
	private Timestamp timestamp;

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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
