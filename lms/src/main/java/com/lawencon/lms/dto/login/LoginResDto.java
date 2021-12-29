package com.lawencon.lms.dto.login;

public class LoginResDto {
	private String token;
	private String usersId;
	private String rolesCode;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	public String getRolesCode() {
		return rolesCode;
	}

	public void setRolesCode(String rolesCode) {
		this.rolesCode = rolesCode;
	}

}
