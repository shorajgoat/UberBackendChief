package com.UberBackend.dto;

import java.time.LocalDateTime;

import com.UberBackend.enums.*;

public class RideResponseDto {
	 private Long id;
	    private String riderName;
	    private String driverName;
	    private String pickupAddress;
	    private String dropAddress;
	    private Double pickupLatitude;
	    private Double pickupLongitude;
	    private Double dropLatitude;
	    private Double dropLongitude;
	    private RideStatus status;
	    private Double fare;
	    private Double distanceKm;
	    private LocalDateTime requestedAt;
	    private LocalDateTime startedAt;
	    private LocalDateTime completedAt;
	    
	    public RideResponseDto() {
	    	
	    }
	    

		public RideResponseDto(Long id, String riderName, String driverName, String pickupAddress, String dropAddress,
				Double pickupLatitude, Double pickupLongitude, Double dropLatitude, Double dropLongitude,
				RideStatus status, Double fare, Double distanceKm, LocalDateTime requestedAt, LocalDateTime startedAt,
				LocalDateTime completedAt) {
			super();
			this.id = id;
			this.riderName = riderName;
			this.driverName = driverName;
			this.pickupAddress = pickupAddress;
			this.dropAddress = dropAddress;
			this.pickupLatitude = pickupLatitude;
			this.pickupLongitude = pickupLongitude;
			this.dropLatitude = dropLatitude;
			this.dropLongitude = dropLongitude;
			this.status = status;
			this.fare = fare;
			this.distanceKm = distanceKm;
			this.requestedAt = requestedAt;
			this.startedAt = startedAt;
			this.completedAt = completedAt;
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRiderName() {
			return riderName;
		}

		public void setRiderName(String riderName) {
			this.riderName = riderName;
		}

		public String getDriverName() {
			return driverName;
		}

		public void setDriverName(String driverName) {
			this.driverName = driverName;
		}

		public String getPickupAddress() {
			return pickupAddress;
		}

		public void setPickupAddress(String pickupAddress) {
			this.pickupAddress = pickupAddress;
		}

		public String getDropAddress() {
			return dropAddress;
		}

		public void setDropAddress(String dropAddress) {
			this.dropAddress = dropAddress;
		}

		public Double getPickupLatitude() {
			return pickupLatitude;
		}

		public void setPickupLatitude(Double pickupLatitude) {
			this.pickupLatitude = pickupLatitude;
		}

		public Double getPickupLongitude() {
			return pickupLongitude;
		}

		public void setPickupLongitude(Double pickupLongitude) {
			this.pickupLongitude = pickupLongitude;
		}

		public Double getDropLatitude() {
			return dropLatitude;
		}

		public void setDropLatitude(Double dropLatitude) {
			this.dropLatitude = dropLatitude;
		}

		public Double getDropLongitude() {
			return dropLongitude;
		}

		public void setDropLongitude(Double dropLongitude) {
			this.dropLongitude = dropLongitude;
		}

		public RideStatus getStatus() {
			return status;
		}

		public void setStatus(RideStatus status) {
			this.status = status;
		}

		public Double getFare() {
			return fare;
		}

		public void setFare(Double fare) {
			this.fare = fare;
		}

		public Double getDistanceKm() {
			return distanceKm;
		}

		public void setDistanceKm(Double distanceKm) {
			this.distanceKm = distanceKm;
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
	    
}
