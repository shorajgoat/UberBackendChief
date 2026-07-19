package com.UberBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.UberBackend.Entity.Payment;



@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRideId(Long rideId);
    List<Payment> findAllByUserIdOrderByCreatedAtDesc(Long userId);
}
