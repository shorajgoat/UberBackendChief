package com.UberBackend.dto;

import java.time.LocalDateTime;

import com.UberBackend.enums.Role;

public class UserResponseDto {
private long id;
private String email;
private String phoneNo;
private String name;
private Role role;
private LocalDateTime createdAt;

public UserResponseDto() {
	
	
}



public UserResponseDto(long id, String email, String phoneNo, String name, Role role, LocalDateTime createdAt) {
	
	this.id = id;
	this.email = email;
	this.phoneNo = phoneNo;
	this.name = name;
	this.role = role;
	this.createdAt = createdAt;
}



public long getId() {
	return id;
}



public void setId(long id) {
	this.id = id;
}



public String getEmail() {
	return email;
}



public void setEmail(String email) {
	this.email = email;
}



public String getPhoneNo() {
	return phoneNo;
}



public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}



public String getName() {
	return name;
}



public void setName(String name) {
	this.name = name;
}



public Role getRole() {
	return role;
}



public void setRole(Role role) {
	this.role = role;
}



public LocalDateTime getCreatedAt() {
	return createdAt;
}



public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}



}
