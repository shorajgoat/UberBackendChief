package com.UberBackend.dto;

import java.time.LocalDateTime;

public class RatingResponseDto {
	 private Long id;
	    private Long rideId;
	    private String riderName;
	    private String driverName;
	    private Integer driverRating;
	    private Integer riderRating;
	    private String driverComment;
	    private String riderComment;
	    private LocalDateTime createdAt;

	    // No-Args Constructor
	    public RatingResponseDto() {}

	    // Getters and Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public Long getRideId() { return rideId; }
	    public void setRideId(Long rideId) { this.rideId = rideId; }

	    public String getRiderName() { return riderName; }
	    public void setRiderName(String riderName) { this.riderName = riderName; }

	    public String getDriverName() { return driverName; }
	    public void setDriverName(String driverName) { this.driverName = driverName; }

	    public Integer getDriverRating() { return driverRating; }
	    public void setDriverRating(Integer driverRating) { this.driverRating = driverRating; }

	    public Integer getRiderRating() { return riderRating; }
	    public void setRiderRating(Integer riderRating) { this.riderRating = riderRating; }

	    public String getDriverComment() { return driverComment; }
	    public void setDriverComment(String driverComment) { this.driverComment = driverComment; }

	    public String getRiderComment() { return riderComment; }
	    public void setRiderComment(String riderComment) { this.riderComment = riderComment; }

	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
