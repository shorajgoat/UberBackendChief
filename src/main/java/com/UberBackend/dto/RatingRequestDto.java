package com.UberBackend.dto;

public class RatingRequestDto {

    private Long rideId;
    private Integer driverRating;
    private Integer riderRating;
    private String driverComment;
    private String riderComment;

    // No-Args Constructor
    public RatingRequestDto() {}

    // Getters and Setters
    public Long getRideId() { return rideId; }
    public void setRideId(Long rideId) { this.rideId = rideId; }

    public Integer getDriverRating() { return driverRating; }
    public void setDriverRating(Integer driverRating) { this.driverRating = driverRating; }

    public Integer getRiderRating() { return riderRating; }
    public void setRiderRating(Integer riderRating) { this.riderRating = riderRating; }

    public String getDriverComment() { return driverComment; }
    public void setDriverComment(String driverComment) { this.driverComment = driverComment; }

    public String getRiderComment() { return riderComment; }
    public void setRiderComment(String riderComment) { this.riderComment = riderComment; }
}
