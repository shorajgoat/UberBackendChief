package com.UberBackend.Entity;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "rider_id", nullable = false)
    private User rider;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @Column(nullable = false)
    private Integer driverRating;

    @Column(nullable = false)
    private Integer riderRating;

    private String driverComment;
    private String riderComment;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // No-Args Constructor
    public Rating() {}

    // Getters and Setters
    public Long getId() { return id; }

    public Ride getRide() { return ride; }
    public void setRide(Ride ride) { this.ride = ride; }

    public User getRider() { return rider; }
    public void setRider(User rider) { this.rider = rider; }

    public User getDriver() { return driver; }
    public void setDriver(User driver) { this.driver = driver; }

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
