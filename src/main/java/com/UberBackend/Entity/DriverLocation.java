package com.UberBackend.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name="drivers_location")
public class DriverLocation{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	private Double latitude;
	private Double longitude;
	private Boolean isAvailable;
	private LocalDateTime lastCreated;
	
	@PrePersist
	@PreUpdate
	public void updatedAt() {
		this.lastCreated=LocalDateTime.now();
	}
	public DriverLocation() {
		
	}
	public DriverLocation(Long id, User user, Double latitude, Double longitude, Boolean isAvailable,
			LocalDateTime lastCreated) {
		this.id = id;
		this.user = user;
		this.latitude = latitude;
		this.longitude = longitude;
		this.isAvailable = isAvailable;
		this.lastCreated = lastCreated;
	}
	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public LocalDateTime getLastCreated() {
		return lastCreated;
	}
	public void setLastCreated(LocalDateTime lastCreated) {
		this.lastCreated = lastCreated;
	}
	
	
	
}