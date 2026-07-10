package com.UberBackend.Entity;

import java.time.LocalDateTime;

import com.UberBackend.enums.DriverStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="driver_profiles")
public class DriverProfile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	@OneToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	@Enumerated(EnumType.STRING)
	private DriverStatus status;
	@Column(nullable=false,unique=true)
	private String license;
	private double rating;
	private LocalDateTime createdAt;
	private int totalRides;
	
	@PrePersist
	public void createdOn() {
		this.createdAt=LocalDateTime.now();
		this.rating= 0.0;
		this.totalRides=0;
	}
	public DriverProfile() {
		
	}
	
	public long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public DriverStatus getStatus() {
		return status;
	}
	public void setStatus(DriverStatus status) {
		this.status = status;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public int getTotalRides() {
		return totalRides;
	}
	public void setTotalRides(int totalRides) {
		this.totalRides = totalRides;
	}
	
	
}
