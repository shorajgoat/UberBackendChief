package com.UberBackend.Entity;

import java.time.LocalDateTime;

import com.UberBackend.enums.RideStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="rides")
public class Ride {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
@ManyToOne
@JoinColumn(name="rider_id",nullable=false)
private User user;

@ManyToOne
@JoinColumn(name="driver_id",nullable=false)
private User Driver;
@Column(nullable=false)
private double pickupLatitude;
@Column(nullable=false)
private double pickupLongitude;
@Column(nullable=false)
private double dropLatitude;
@Column(nullable=false)
private double dropLongitude;



@Enumerated(EnumType.STRING)
@Column(nullable=false)
private RideStatus rideStatus;
@Column(nullable=false)
private String pickUpAddress;
@Column(nullable=false)
private String dropAddress;
private double fare;
private double distancekm;
private LocalDateTime requestedAt;
private LocalDateTime startedAt;
private LocalDateTime completedAt;

public Ride() {
	
}

public Ride(User user, User driver, double pickupLatitude, double pickupLongitude, double dropLatitude,
		double dropLongitude, RideStatus rideStatus, double fare, double distancekm, LocalDateTime requestedAt,
		LocalDateTime startedAt, LocalDateTime completedAt) {
	this.user = user;
	Driver = driver;
	this.pickupLatitude = pickupLatitude;
	this.pickupLongitude = pickupLongitude;
	this.dropLatitude = dropLatitude;
	this.dropLongitude = dropLongitude;
	this.rideStatus = rideStatus;
	this.fare = fare;
	this.distancekm = distancekm;
	this.requestedAt = requestedAt;
	this.startedAt = startedAt;
	this.completedAt = completedAt;
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

public User getDriver() {
	return Driver;
}

public void setDriver(User driver) {
	Driver = driver;
}

public double getPickupLatitude() {
	return pickupLatitude;
}

public void setPickupLatitude(double pickupLatitude) {
	this.pickupLatitude = pickupLatitude;
}

public double getPickupLongitude() {
	return pickupLongitude;
}

public void setPickupLongitude(double pickupLongitude) {
	this.pickupLongitude = pickupLongitude;
}

public double getDropLatitude() {
	return dropLatitude;
}

public void setDropLatitude(double dropLatitude) {
	this.dropLatitude = dropLatitude;
}

public double getDropLongitude() {
	return dropLongitude;
}

public void setDropLongitude(double dropLongitude) {
	this.dropLongitude = dropLongitude;
}

public RideStatus getRideStatus() {
	return rideStatus;
}

public void setRideStatus(RideStatus rideStatus) {
	this.rideStatus = rideStatus;
}

public double getFare() {
	return fare;
}

public void setFare(double fare) {
	this.fare = fare;
}

public double getDistancekm() {
	return distancekm;
}

public void setDistancekm(double distancekm) {
	this.distancekm = distancekm;
}

public LocalDateTime getRequestedAt() {
	return requestedAt;
}

public void setRequestedAt(LocalDateTime requestedAt) {
	this.requestedAt = requestedAt;
}

public LocalDateTime getStartedAt() {
	return startedAt;
}

public void setStartedAt(LocalDateTime startedAt) {
	this.startedAt = startedAt;
}

public LocalDateTime getCompletedAt() {
	return completedAt;
}

public void setCompletedAt(LocalDateTime completedAt) {
	this.completedAt = completedAt;
}

public String getPickUpAddress() {
	return pickUpAddress;
}

public void setPickUpAddress(String pickUpAddress) {
	this.pickUpAddress = pickUpAddress;
}

public String getDropAddress() {
	return dropAddress;
}

public void setDropAddress(String dropAddress) {
	this.dropAddress = dropAddress;
}



}
