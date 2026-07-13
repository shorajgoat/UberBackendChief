package com.UberBackend.dto;

public class DriverProfileRequestDto {
	private String licenseNumber;
	private String model;
	private String plateNumber;
	private String colour;
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

	public String getColour() {
		return colour;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	
}
