package com.UberBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UberBackend.Entity.*;
import com.UberBackend.config.*;
import com.UberBackend.dto.ApiResponseDto;
import com.UberBackend.dto.RideRequestDto;
import com.UberBackend.dto.RideResponseDto;
import com.UberBackend.service.RideService;
import com.UberBackend.utils.JwtUtil;

@RestController
@RequestMapping("/api/v1/rides")

public class RideController {
	 @Autowired
	    private RideService rideService;

	    @Autowired
	    private JwtUtil jwtUtil;

	    // Rider books a ride
	    @PostMapping("/book")
	    public ResponseEntity<ApiResponseDto<RideResponseDto>> bookRide(
	            @RequestHeader("Authorization") String authHeader,
	            @RequestBody RideRequestDto dto) {
	        String email = extractEmail(authHeader);
	        RideResponseDto response = rideService.bookRide(email, dto);
	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(ApiResponseDto.success("Ride booked successfully", response));
	    }

	    // Driver accepts ride
	    @PutMapping("/accept/{rideId}")
	    public ResponseEntity<ApiResponseDto<RideResponseDto>> acceptRide(
	            @RequestHeader("Authorization") String authHeader,
	            @PathVariable Long rideId) {
	        String email = extractEmail(authHeader);
	        RideResponseDto response = rideService.acceptRide(email, rideId);
	        return ResponseEntity.ok(ApiResponseDto.success("Ride accepted", response));
	    }

	    // Driver starts ride
	    @PutMapping("/start/{rideId}")
	    public ResponseEntity<ApiResponseDto<RideResponseDto>> startRide(
	            @RequestHeader("Authorization") String authHeader,
	            @PathVariable Long rideId) {
	        String email = extractEmail(authHeader);
	        RideResponseDto response = rideService.startRide(email, rideId);
	        return ResponseEntity.ok(ApiResponseDto.success("Ride started", response));
	    }

	    // Driver completes ride
	    @PutMapping("/complete/{rideId}")
	    public ResponseEntity<ApiResponseDto<RideResponseDto>> completeRide(
	            @RequestHeader("Authorization") String authHeader,
	            @PathVariable Long rideId) {
	        String email = extractEmail(authHeader);
	        RideResponseDto response = rideService.completeRide(email, rideId);
	        return ResponseEntity.ok(ApiResponseDto.success("Ride completed", response));
	    }

	    // Cancel ride
	    @PutMapping("/cancel/{rideId}")
	    public ResponseEntity<ApiResponseDto<RideResponseDto>> cancelRide(
	            @RequestHeader("Authorization") String authHeader,
	            @PathVariable Long rideId) {
	        String email = extractEmail(authHeader);
	        RideResponseDto response = rideService.cancelRide(email, rideId);
	        return ResponseEntity.ok(ApiResponseDto.success("Ride cancelled", response));
	    }

	    // Rider history
	    @GetMapping("/history/rider")
	    public ResponseEntity<ApiResponseDto<List<RideResponseDto>>> riderHistory(
	            @RequestHeader("Authorization") String authHeader) {
	        String email = extractEmail(authHeader);
	        List<RideResponseDto> response = rideService.getRiderHistory(email);
	        return ResponseEntity.ok(ApiResponseDto.success("Rider history fetched", response));
	    }

	    // Driver history
	    @GetMapping("/history/driver")
	    public ResponseEntity<ApiResponseDto<List<RideResponseDto>>> driverHistory(
	            @RequestHeader("Authorization") String authHeader) {
	        String email = extractEmail(authHeader);
	        List<RideResponseDto> response = rideService.getDriverHistory(email);
	        return ResponseEntity.ok(ApiResponseDto.success("Driver history fetched", response));
	    }

	    private String extractEmail(String authHeader) {
	        String token = authHeader.substring(7);
	        return jwtUtil.extractEmail(token);
	    }

}
