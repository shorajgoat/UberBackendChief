package com.UberBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.UberBackend.Entity.Ride;
import com.UberBackend.enums.RideStatus;
@Repository
public interface RideRepository extends JpaRepository<Ride,Long>{

    List<Ride> findAllByUserIdOrderByRequestedAtDesc(Long userId);

    List<Ride> findAllByDriverIdOrderByRequestedAtDesc(Long driverId);

    List<Ride> findAllByRideStatus(RideStatus rideStatus);

}
