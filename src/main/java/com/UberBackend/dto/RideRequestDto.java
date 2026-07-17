package com.UberBackend.dto;

public class RideRequestDto {
private double pickupLatitude;
private double pickupLongitude;
private double dropLatitude;
private double dropLongitude;
private String pickUpAdress;
private String dropAdress;


public RideRequestDto() {
	
}


public RideRequestDto(double pickupLatitude, double pickupLongitude, double dropLatitude, double dropLongitude,
		String pickUpAdress, String dropAdress) {
	this.pickupLatitude = pickupLatitude;
	this.pickupLongitude = pickupLongitude;
	this.dropLatitude = dropLatitude;
	this.dropLongitude = dropLongitude;
	this.pickUpAdress = pickUpAdress;
	this.dropAdress = dropAdress;
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


public String getPickUpAdress() {
	return pickUpAdress;
}


public void setPickUpAdress(String pickUpAdress) {
	this.pickUpAdress = pickUpAdress;
}


public String getDropAdress() {
	return dropAdress;
}


public void setDropAdress(String dropAdress) {
	this.dropAdress = dropAdress;
}




}
