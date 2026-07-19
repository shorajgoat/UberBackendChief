package com.UberBackend.dto;

import java.time.LocalDateTime;

import com.UberBackend.enums.PaymentStatus;


public class PaymentResponseDto {

    private Long id;
    private Long rideId;
    private String riderName;
    private Double amount;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    // No-Args Constructor
    public PaymentResponseDto() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRideId() { return rideId; }
    public void setRideId(Long rideId) { this.rideId = rideId; }

    public String getRiderName() { return riderName; }
    public void setRiderName(String riderName) { this.riderName = riderName; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
