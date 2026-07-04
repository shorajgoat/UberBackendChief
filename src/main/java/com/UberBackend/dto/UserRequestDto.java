package com.UberBackend.dto;

import com.UberBackend.enums.Role;

public class UserRequestDto {
	private  String phoneNo;
	private  String email;
	private String name;
	private  String password;
	
	public Role role;
public UserRequestDto() {
	
}
	public UserRequestDto(String phoneNo, String email, String name, String password, Role role) {
		
		this.phoneNo = phoneNo;
		this.email = email;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	

}
