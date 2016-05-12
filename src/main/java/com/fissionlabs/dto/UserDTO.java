package com.fissionlabs.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fissionlabs.model.User;

/**
 * Data transfer object for user details. Used for access/update of user
 * details.
 * 
 * @author Karthik
 *
 */
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

	private String id;
	private String username;
	private String name;
	private String password;
	private Long createdAt;
	private Boolean accountLocked;
	private String phoneNumber;
	private String userRole;
	private Long expires;
	private String profileImage;
	private Boolean hasMeta;
	private Map<String, String> userActivity;
	private Map<String, String> userMeta;

	public UserDTO() {

	}

	private UserDTO(UserDTOBuilder userDTOBuilder) {
		this.id = userDTOBuilder.id;
		this.username = userDTOBuilder.username;
		this.name = userDTOBuilder.name;
		this.createdAt = userDTOBuilder.createdAt;
		this.accountLocked = userDTOBuilder.accountLocked;
		this.phoneNumber = userDTOBuilder.phoneNumber;
		this.userRole = userDTOBuilder.userRole;
		this.profileImage = userDTOBuilder.profileImage;
		this.expires = userDTOBuilder.expires;

		this.hasMeta = this.userMeta != null && this.userMeta.size() > 0;
	}

	/**
	 * 
	 * @author Adil
	 *
	 */
	public static class UserDTOBuilder {

		private String id;
		private String username;
		private String name;
		private Long createdAt;
		private Boolean accountLocked;
		private String phoneNumber;
		private String userRole;
		private String profileImage;
		private Long expires;

		public UserDTOBuilder(User user) {
			this.id = user.getId() == null ? null : user.getId();
			this.username = user.getUsername();
			this.name = user.getName();
			this.createdAt = user.getCreatedAt() == null ? null : user
					.getCreatedAt().getTime();
			this.phoneNumber = user.getPhoneNumber();
			this.profileImage = user.getProfileImage();
			this.userRole = user.getUserRole();

		}

		public UserDTOBuilder() {

		}

		public UserDTOBuilder id(String id) {
			this.id = id;
			return this;
		}

		public UserDTOBuilder expires(Long expires) {
			this.expires = expires;
			return this;
		}

		public UserDTOBuilder username(String username) {
			this.username = username;
			return this;
		}

		public UserDTOBuilder name(String name) {
			this.name = name;
			return this;
		}

		public UserDTOBuilder createdAt(Long createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public UserDTOBuilder accountLocked(Boolean accountLocked) {
			this.accountLocked = accountLocked;
			return this;
		}

		public UserDTOBuilder phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public UserDTOBuilder userRole(String userRole) {
			this.userRole = userRole;
			return this;
		}

		public UserDTOBuilder profileImage(String profileImage) {
			this.profileImage = profileImage;
			return this;
		}

		public UserDTO build() {
			return new UserDTO(this);
		}
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public Boolean getAccountLocked() {
		return accountLocked;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Long getExpires() {
		return expires;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Map<String, String> getUserMeta() {
		return userMeta;
	}

	public void setUserMeta(Map<String, String> userMeta) {
		this.userMeta = userMeta;
	}

	public Map<String, String> getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(Map<String, String> userActivity) {
		this.userActivity = userActivity;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Boolean getHasMeta() {
		return hasMeta;
	}

	public void setHasMeta(Boolean hasMeta) {
		this.hasMeta = hasMeta;
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
