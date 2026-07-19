package com.UberBackend.exceptions;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UberBackend.Entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByRideId(Long rideId);
    List<Rating> findAllByDriverId(Long driverId);

    @Query("SELECT AVG(r.driverRating) FROM Rating r WHERE r.driver.id = :driverId")
    Double findAverageDriverRating(Long driverId);
}
