package com.fissionlabs.model;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Document(collection = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_ROLE = "ROLE_USER";

	@Id
	@Field(value = "id")
	private String id;

	@Field(value = "username")
	private String username;

	@Field(value = "name")
	private String name;

	@JsonIgnore
	@Field(value = "password")
	private String password;

	// @Temporal(TemporalType.TIMESTAMP)
	@Field(value = "created_at")
	private Date createdAt;

	@NotNull
	@Field(value = "is_account_enabled")
	private boolean accountEnabled = false;

	@Field(value = "is_account_locked")
	private Boolean accountLocked;

	@Field(value = "is_account_expired")
	private Boolean accountExpired;

	@Field(value = "phone_number")
	private String phoneNumber;

	@Field(value = "profile_image")
	// , FieldDefinition = "text")
	private String profileImage;

	@Field(value = "user_role")
	private String userRole = DEFAULT_ROLE;

	@Transient
	private Long expires;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	public void setAccountLocked(Boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return accountEnabled;
	}

	@Override
	@JsonIgnore
	public Collection<Authority> getAuthorities() {
		Collection<Authority> userAuthorities = new LinkedList<Authority>();
		userAuthorities.add(new Authority(userRole));
		return userAuthorities;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Long getExpires() {
		return expires;
	}

	public void setExpires(Long expires) {
		this.expires = expires;
	}

	public boolean isAccountEnabled() {
		return accountEnabled;
	}

	public void setAccountEnabled(boolean accountEnabled) {
		this.accountEnabled = accountEnabled;
	}

	public Boolean getAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@JsonProperty
	public String getUserRole() {
		return userRole;
	}

	@JsonIgnore
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new IllegalStateException(e);
		}
	}
}
