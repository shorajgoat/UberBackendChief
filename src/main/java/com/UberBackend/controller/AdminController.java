package com.UberBackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UberBackend.Entity.User;
import com.UberBackend.dto.ApiResponseDto;
import com.UberBackend.dto.UserResponseDto;
import com.UberBackend.enums.DriverStatus;
import com.UberBackend.enums.RideStatus;
import com.UberBackend.enums.Role;
import com.UberBackend.repository.DriverProfileRepository;
import com.UberBackend.repository.RideRepository;
import com.UberBackend.repository.UserRepository;



@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {


    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private DriverProfileRepository driverProfileRepo;

    // Get all users
    @GetMapping( )
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getAllUsers() {
        List<UserResponseDto> users = userRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponseDto.success("All users fetched", users));
    }

    // Get all drivers
    @GetMapping("/drivers")
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getAllDrivers() {
        List<UserResponseDto> drivers = userRepo.findAllByRole(Role.DRIVER)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponseDto.success("All drivers fetched", drivers));
    }

    // Get all riders
    @GetMapping("/riders")
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getAllRiders() {
        List<UserResponseDto> riders = userRepo.findAllByRole(Role.USER)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponseDto.success("All riders fetched", riders));
    }

    // Platform stats
    @GetMapping("/stats")
    public ResponseEntity<ApiResponseDto<Map<String, Object>>> getStats() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalUsers", userRepo.count());
        stats.put("totalDrivers", userRepo.findAllByRole(Role.DRIVER).size());
        stats.put("totalRiders", userRepo.findAllByRole(Role.USER).size());
        stats.put("totalRides", rideRepo.count());
        stats.put("completedRides", rideRepo.findAllByRideStatus(RideStatus.COMPLETED).size());
        stats.put("cancelledRides", rideRepo.findAllByRideStatus(RideStatus.CANCELLED).size());
        stats.put("pendingDrivers", driverProfileRepo.findAllByStatus(DriverStatus.PENDING).size());
        stats.put("approvedDrivers", driverProfileRepo.findAllByStatus(DriverStatus.APPROVED).size());

        return ResponseEntity.ok(ApiResponseDto.success("Platform stats", stats));
    }

    // Get all rides
    @GetMapping("/rides")
    public ResponseEntity<ApiResponseDto<Long>> getTotalRides() {
        Long total = rideRepo.count();
        return ResponseEntity.ok(ApiResponseDto.success("Total rides", total));
    }

    private UserResponseDto mapToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhoneNo(user.getPhoneNo());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    


    }
}
