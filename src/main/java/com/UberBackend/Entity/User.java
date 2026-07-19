package com.UberBackend.Entity;

import java.time.LocalDateTime;

import com.UberBackend.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false,unique=true)
	private String phoneNo;
	@Column(nullable=false,unique=true)
	private String email;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(nullable=false)
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
@PrePersist
public void  created_on() {
	this.createdAt=LocalDateTime.now();
	this.updatedAt=LocalDateTime.now();
}

@PreUpdate
public void updated_on() {
	this.updatedAt=LocalDateTime.now();
}
public User() {
	//no args constructor//
}

public User(String phoneNo, String email, String name, Role role, String password, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
	this.phoneNo = phoneNo;
	this.email = email;
	this.name = name;
	this.role = role;
	this.password = password;
	this.createdAt = createdAt;
	this.updatedAt = updatedAt;
}
public Long getId() {
	return id;
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

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public LocalDateTime getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
	this.createdAt = createdAt;
}

public LocalDateTime getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
}




}
