package com.UberBackend.dto;

import com.UberBackend.enums.DriverStatus;

public class DriverProfileResponseDto {
	private Long id;
	private String name;
	private String email;
	private String licenseNumber;
	private String model;
	private String plateNumber;
	private String colour;
	private String vehicleType;
	private DriverStatus status;
	private Integer totalRides;
	public DriverProfileResponseDto() {
		
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setStatus(DriverStatus status) {
		this.status = status;
	}

	public void setTotalRides(Integer totalRides) {
		this.totalRides = totalRides;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	private Double rating;
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public String getModel() {
		return model;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getColour() {
		return colour;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public DriverStatus getStatus() {
		return status;
	}

	public Integer getTotalRides() {
		return totalRides;
	}

	public Double getRating() {
		return rating;
	}

	
	
}
