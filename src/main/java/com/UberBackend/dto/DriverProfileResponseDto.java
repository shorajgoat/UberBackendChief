package com.UberBackend.dto;

import com.UberBackend.enums.DriverStatus;

public class DriverProfileResponseDto {
	private Long id;
	private String name;
	private String email;
	private String licenseNumber;
	private String model;
	private String plateNumber;
	private String color;
	private String vehicleType;
	private DriverStatus status;
	private Integer totalRides;
	private Double rating;
	
	public DriverProfileResponseDto() {
		
	}

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

	public String getColor() {
		return color;
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
