package com.UberBackend.dto;

public class DriverProfileRequestDto {
	private String licenseNumber;
	private String model;
	private String plateNumber;
	private String color;
	private String vehicleType;
	
	public DriverProfileRequestDto() {
		
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

	
}
