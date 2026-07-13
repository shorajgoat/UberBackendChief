package com.UberBackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UberBackend.Entity.DriverProfile;
import com.UberBackend.Entity.User;
import com.UberBackend.Entity.Vehicle;
import com.UberBackend.dto.DriverProfileRequestDto;
import com.UberBackend.dto.DriverProfileResponseDto;
import com.UberBackend.enums.DriverStatus;
import com.UberBackend.enums.Role;
import com.UberBackend.exceptions.DuplicateResourceException;
import com.UberBackend.exceptions.InvalidCredentialsException;
import com.UberBackend.exceptions.ResourceNotFoundException;
import com.UberBackend.repository.DriverProfileRepository;
import com.UberBackend.repository.UserRepository;
import com.UberBackend.repository.VehicleRepository;

@Service
public class DriverService {

    private final UserRepository userRepo;
    private final DriverProfileRepository driverProfileRepo;
    private final VehicleRepository vehicleRepo;

    public DriverService(UserRepository userRepo,
                         DriverProfileRepository driverProfileRepo,
                         VehicleRepository vehicleRepo) {
        this.userRepo = userRepo;
        this.driverProfileRepo = driverProfileRepo;
        this.vehicleRepo = vehicleRepo;
    }

    private DriverProfileResponseDto mapToDto(DriverProfile profile, Vehicle vehicle) {
        DriverProfileResponseDto dto = new DriverProfileResponseDto();

        dto.setId(profile.getId());
        dto.setName(profile.getUser().getName());
        dto.setEmail(profile.getUser().getEmail());
        dto.setLicenseNumber(profile.getLicense());
        dto.setStatus(profile.getStatus());
        dto.setRating(profile.getRating());
        dto.setTotalRides(profile.getTotalRides());

        dto.setModel(vehicle.getModel());
        dto.setPlateNumber(vehicle.getPlateNumber());
        dto.setColour(vehicle.getColour());
        dto.setVehicleType(vehicle.getVehicleType());

        return dto;
    }

    @Transactional
    public DriverProfileResponseDto registerDriver(String email,
                                                   DriverProfileRequestDto dto) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (user.getRole() != Role.RIDER) {
            throw new InvalidCredentialsException(
                    "Only DRIVER accounts can register a driver profile");
        }

        if (driverProfileRepo.existsByUserId(user.getId())) {
            throw new DuplicateResourceException("Driver profile already exists");
        }

        if (vehicleRepo.existsByPlateNumber(dto.getPlateNumber())) {
            throw new DuplicateResourceException("Plate number already registered");
        }

        DriverProfile profile = new DriverProfile();
        profile.setUser(user);
        profile.setLicense(dto.getLicenseNumber());
        driverProfileRepo.save(profile);

        Vehicle vehicle = new Vehicle();
        vehicle.setDriverProfile(profile);
        vehicle.setModel(dto.getModel());
        vehicle.setPlateNumber(dto.getPlateNumber());
        vehicle.setColour(dto.getColour());
        vehicle.setVehicleType(dto.getVehicleType());
        vehicleRepo.save(vehicle);

        return mapToDto(profile, vehicle);
    }

    public DriverProfileResponseDto getDriverProfile(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DriverProfile profile = driverProfileRepo.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found"));

        Vehicle vehicle = vehicleRepo.findByDriverProfileId(profile.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        return mapToDto(profile, vehicle);
    }

    @Transactional
    public DriverProfileResponseDto approveDriver(Long driverProfileId) {

        DriverProfile profile = driverProfileRepo.findById(driverProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found"));

        profile.setStatus(DriverStatus.APPROVED);

        Vehicle vehicle = vehicleRepo.findByDriverProfileId(profile.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        return mapToDto(profile, vehicle);
    }

    @Transactional
    public DriverProfileResponseDto rejectDriver(Long driverProfileId) {

        DriverProfile profile = driverProfileRepo.findById(driverProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver profile not found"));

        profile.setStatus(DriverStatus.REJECTED);

        Vehicle vehicle = vehicleRepo.findByDriverProfileId(profile.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        return mapToDto(profile, vehicle);
    }
}