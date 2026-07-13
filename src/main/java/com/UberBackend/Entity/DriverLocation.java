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
@Table(name="driver_location")
public class DriverLocation {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	    private Double latitude;
	    private Double longitude;
	    private Boolean isAvailable;
	    private LocalDateTime lastUpdated;

	    @PrePersist
	    @PreUpdate
	    public void onUpdate() {
	        this.lastUpdated = LocalDateTime.now();
	    }

	    // No-Args Constructor
	    public DriverLocation() {}

	    // Getters and Setters
	    public Long getId() { return id; }

	    public User getUser() { return user; }
	    public void setUser(User user) { this.user = user; }

	    public Double getLatitude() { return latitude; }
	    public void setLatitude(Double latitude) { this.latitude = latitude; }

	    public Double getLongitude() { return longitude; }
	    public void setLongitude(Double longitude) { this.longitude = longitude; }

	    public Boolean getIsAvailable() { return isAvailable; }
	    public void setIsAvailable(Boolean isAvailable) { this.isAvailable = isAvailable; }

	    public LocalDateTime getLastUpdated() { return lastUpdated; }
	    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
