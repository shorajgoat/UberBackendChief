package com.UberBackend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UberBackend.repository.DriverLocationRepository;
import com.UberBackend.repository.RideRepository;
import com.UberBackend.repository.UserRepository;
import com.UberBackend.utils.HaversineUtil;
import com.UberBackend.Entity.*;
import com.UberBackend.enums.*;
import com.UberBackend.exceptions.*;
import com.UberBackend.dto.RideRequestDto;
import com.UberBackend.dto.RideResponseDto;
import com.UberBackend.Entity.Ride;
import com.UberBackend.repository.DriverLocationRepository;



@Service
public class RideService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RideRepository rideRepo;
	@Autowired
	private DriverLocationRepository locationRepo;
	@Autowired
	private HaversineUtil haversineUtil;
	
	
private RideResponseDto mapToDto(Ride ride) {
	        RideResponseDto dto = new RideResponseDto();
	        dto.setId(ride.getId());
	        dto.setRiderName(ride.getRider().getName());
	        dto.setDriverName(ride.getDriver() != null ?
	                ride.getDriver().getName() : "Not assigned");
	        dto.setPickupAddress(ride.getPickUpAddress());
	        dto.setDropAddress(ride.getDropAddress());
	        dto.setPickupLatitude(ride.getPickupLatitude());
	        dto.setPickupLongitude(ride.getPickupLongitude());
	        dto.setDropLatitude(ride.getDropLatitude());
	        dto.setDropLongitude(ride.getDropLongitude());
	        dto.setStatus(ride.getRideStatus());
	        dto.setFare(ride.getFare());
	        dto.setDistanceKm(ride.getDistancekm());
	        dto.setRequestedAt(ride.getRequestedAt());
	        dto.setStartedAt(ride.getStartedAt());
	        dto.setCompletedAt(ride.getCompletedAt());
	        return dto;
	    }
	
private void validateDriverForRide(String driverEmail, Ride ride) {
    if (!ride.getDriver().getEmail().equals(driverEmail)) {
        throw new InvalidCredentialsException("You are not assigned to this ride");
    }
    
}
private Ride getRideAndValidateStatus(Long rideId, RideStatus expectedStatus) {
    Ride ride = rideRepo.findById(rideId)
            .orElseThrow(() -> new ResourceNotFoundException("Ride not found"));

    if (ride.getRideStatus() != expectedStatus) {
        throw new InvalidCredentialsException(
                "Ride is not in " + expectedStatus + " status");
    }
    return ride;
}
private User findNearestDriver(double pickupLat, double pickupLon) {
    List<DriverLocation> availableDrivers = locationRepo.findAllAvailableDrivers();

    if (availableDrivers.isEmpty()) {
        throw new ResourceNotFoundException("No drivers available nearby");
    }

    DriverLocation nearest = null;
    double minDistance = Double.MAX_VALUE;

    for (DriverLocation dl : availableDrivers) {
        double dist = haversineUtil.calculateDistance(
                pickupLat, pickupLon,
                dl.getLatitude(), dl.getLongitude()
        );
        if (dist < minDistance) {
            minDistance = dist;
            nearest = dl;
        }
    }

    return nearest.getUser();
}

private double calculateFare(double distanceKm) {
    double baseFare = 50.0;
    double perKmRate = 12.0;
    return baseFare + (distanceKm * perKmRate);
}    public RideResponseDto bookRide(String riderEmail, RideRequestDto dto) {

    User rider = userRepo.findByEmail(riderEmail)
            .orElseThrow(() -> new ResourceNotFoundException("Rider not found"));

    double distance = haversineUtil.calculateDistance(
            dto.getPickupLatitude(), dto.getPickupLongitude(),
            dto.getDropLatitude(), dto.getDropLongitude()
    );

    double fare = calculateFare(distance);

    User nearestDriver = findNearestDriver(
            dto.getPickupLatitude(),
            dto.getPickupLongitude()
    );

    Ride ride = new Ride();
    ride.setRider(rider);
    ride.setDriver(nearestDriver);
    ride.setPickupLatitude(dto.getPickupLatitude());
    ride.setPickupLongitude(dto.getPickupLongitude());
    ride.setDropLatitude(dto.getDropLatitude());
    ride.setDropLongitude(dto.getDropLongitude());
    ride.setPickUpAddress(dto.getPickUpAdress());
    ride.setDropAddress(dto.getDropAdress());
    ride.setDistancekm(distance);
    ride.setFare(fare);

    rideRepo.save(ride);
    return mapToDto(ride);
}

// Driver accepts ride
@Transactional
public RideResponseDto acceptRide(String driverEmail, Long rideId) {
    Ride ride = getRideAndValidateStatus(rideId, RideStatus.REQUESTED);
    validateDriverForRide(driverEmail, ride);
    ride.setRideStatus(RideStatus.ACCEPTED);
    rideRepo.save(ride);
    return mapToDto(ride);
}

// Driver starts ride
@Transactional
public RideResponseDto startRide(String driverEmail, Long rideId) {
    Ride ride = getRideAndValidateStatus(rideId, RideStatus.ACCEPTED);
    validateDriverForRide(driverEmail, ride);
    ride.setRideStatus(RideStatus.STARTED);
    ride.setStartedAt(LocalDateTime.now());
    rideRepo.save(ride);
    return mapToDto(ride);
}

// Driver completes ride
@Transactional
public RideResponseDto completeRide(String driverEmail, Long rideId) {
    Ride ride = getRideAndValidateStatus(rideId, RideStatus.STARTED);
    validateDriverForRide(driverEmail, ride);
    ride.setRideStatus(RideStatus.COMPLETED);
    ride.setCompletedAt(LocalDateTime.now());
    rideRepo.save(ride); 
    return mapToDto(ride);
}

// Rider or driver cancels ride
@Transactional
public RideResponseDto cancelRide(String email, Long rideId) {
    Ride ride = rideRepo.findById(rideId)
            .orElseThrow(() -> new ResourceNotFoundException("Ride not found"));

    if (ride.getRideStatus() == RideStatus.COMPLETED) {
        throw new InvalidCredentialsException("Cannot cancel a completed ride");
    }

    ride.setRideStatus(RideStatus.CANCELLED);
    rideRepo.save(ride);
    return mapToDto(ride);
}

// Get rider ride history
public List<RideResponseDto> getRiderHistory(String email) {
    User rider = userRepo.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return rideRepo.findAllByRiderIdOrderByRequestedAtDesc(rider.getId())
            .stream().map(this::mapToDto).collect(Collectors.toList());
}

// Get driver ride history
public List<RideResponseDto> getDriverHistory(String email) {
    User driver = userRepo.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return rideRepo.findAllByDriverIdOrderByRequestedAtDesc(driver.getId())
            .stream().map(this::mapToDto).collect(Collectors.toList());
}

	

}
