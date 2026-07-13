package com.UberBackend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name="driver_profile_id")
	private DriverProfile driverProfile;
	@Column(nullable=false)
	private String model;
	@Column(nullable=false)
	private String plateNumber;
	@Column(nullable=false)
	private String colour;
	@Column(nullable=false)
	private String vehicleType;
	public Vehicle() {
		
	}
	public Long getId() {
		return id;
	}
	public DriverProfile getDriverProfile() {
		return driverProfile;
	}
	public void setDriverProfile(DriverProfile driverProfile) {
		this.driverProfile = driverProfile;
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
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	
	
}
