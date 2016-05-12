package com.fissionlabs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="user")
public class User1 {
	
	@Id
	
	private String id;
	@Field("karthik")
	private String name;
	private String email;
	
	
	public User1(){}
	
	public User1(String name, String email) {
		
		this.setName(name);
		this.setEmail(email);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
