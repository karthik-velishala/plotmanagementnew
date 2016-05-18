package com.fissionlabs.dto;

public class LoginResponseDTO {

private String clientId;
private String code;
private String redirectUri;
public String getClientId() {
	return clientId;
}
public void setClientId(String clientId) {
	this.clientId = clientId;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getRedirectUri() {
	return redirectUri;
}
public void setRedirectUri(String redirectUri) {
	this.redirectUri = redirectUri;
}
public LoginResponseDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public LoginResponseDTO(String clientId, String code, String redirectUri) {
	super();
	this.clientId = clientId;
	this.code = code;
	this.redirectUri = redirectUri;
}

}
