package com.UberBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UberBackend.Entity.DriverProfile;
import com.UberBackend.Entity.Rating;
import com.UberBackend.Entity.Ride;
import com.UberBackend.dto.RatingRequestDto;
import com.UberBackend.dto.RatingResponseDto;
import com.UberBackend.enums.RideStatus;
import com.UberBackend.exceptions.DuplicateResourceException;
import com.UberBackend.exceptions.InvalidCredentialsException;
import com.UberBackend.exceptions.RatingRepository;
import com.UberBackend.repository.DriverProfileRepository;
import com.UberBackend.repository.RideRepository;
import com.UberBackend.exceptions.*;


@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private DriverProfileRepository driverProfileRepo;

    @Transactional
    public RatingResponseDto submitRating(String email, RatingRequestDto dto) {

        Ride ride = rideRepo.findById(dto.getRideId())
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found"));

        if (ride.getRideStatus() != RideStatus.COMPLETED) {
            throw new InvalidCredentialsException("Can only rate completed rides");
        }

        if (ratingRepo.findByRideId(ride.getId()).isPresent()) {
            throw new DuplicateResourceException("Rating already submitted for this ride");
        }

        Rating rating = new Rating();
        rating.setRide(ride);
        rating.setRider(ride.getUser());
        rating.setDriver(ride.getDriver());
        rating.setDriverRating(dto.getDriverRating());
        rating.setRiderRating(dto.getRiderRating());
        rating.setDriverComment(dto.getDriverComment());
        rating.setRiderComment(dto.getRiderComment());
        ratingRepo.save(rating);

        updateDriverAverageRating(ride.getDriver().getId());

        return mapToDto(rating);
    }

    public Double getDriverAverageRating(Long driverUserId) {
        DriverProfile profile = driverProfileRepo.findByUserId(driverUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found"));

        return profile.getRating();
    }

    private void updateDriverAverageRating(Long driverUserId) {
        Double avg = ratingRepo.findAverageDriverRating(driverUserId);
        if (avg != null) {
            DriverProfile profile = driverProfileRepo.findByUserId(driverUserId)
                    .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found"));
            profile.setRating(Math.round(avg * 10.0) / 10.0);
            driverProfileRepo.save(profile);
        }
    }

    private RatingResponseDto mapToDto(Rating rating) {
        RatingResponseDto dto = new RatingResponseDto();
        dto.setId(rating.getId());
        dto.setRideId(rating.getRide().getId());
        dto.setRiderName(rating.getRider().getName());
        dto.setDriverName(rating.getDriver().getName());
        dto.setDriverRating(rating.getDriverRating());
        dto.setRiderRating(rating.getRiderRating());
        dto.setDriverComment(rating.getDriverComment());
        dto.setRiderComment(rating.getRiderComment());
        dto.setCreatedAt(rating.getCreatedAt());
        return dto;
    }
}
