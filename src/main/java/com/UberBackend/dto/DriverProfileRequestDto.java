package com.UberBackend.dto;

public class DriverProfileRequestDto {
	private String licenseNumber;
	private String model;
	private String plateNumber;
	private String color;
	private String vehicleType;
	
	public DriverProfileRequestDto() {
		
	}

	public DriverProfileRequestDto(String licenseNumber, String model, String plateNumber, String color,
			String vehicleType) {
		super();
		this.licenseNumber = licenseNumber;
		this.model = model;
		this.plateNumber = plateNumber;
		this.color = color;
		this.vehicleType = vehicleType;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
}
