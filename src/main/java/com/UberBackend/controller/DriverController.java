package com.UberBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UberBackend.dto.ApiResponseDto;
import com.UberBackend.dto.DriverProfileRequestDto;
import com.UberBackend.dto.DriverProfileResponseDto;
import com.UberBackend.repository.DriverLocationRepository;
import com.UberBackend.repository.DriverProfileRepository;
import com.UberBackend.repository.UserRepository;
import com.UberBackend.repository.VehicleRepository;
import com.UberBackend.service.DriverService;
import com.UberBackend.utils.JwtUtil;
import com.UberBackend.Entity.*;

@RestController
@RequestMapping("api/v1/drivers")
public class DriverController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DriverService driverService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private DriverLocationRepository locationRepo;
	public String extractEmail(String authHeader) {
		String token=authHeader.substring(7);
		return jwtUtil.extractEmail(token);
	}
	
@PostMapping("/register")
public ResponseEntity<ApiResponseDto<DriverProfileResponseDto>>register(
		@RequestHeader ("Authorization") String authHeader,
		@RequestBody DriverProfileRequestDto dto){
	String email=extractEmail(authHeader);
	DriverProfileResponseDto response=driverService.register(email, dto);
	return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponseDto.success("Successfully registered driver profile",response));
}

@PostMapping("/me")
public ResponseEntity<ApiResponseDto<DriverProfileResponseDto>>getDriverProfile(
		@RequestHeader ("Authorization")String authHeader){
	String email=extractEmail(authHeader);
	DriverProfileResponseDto response=driverService.getDriverProfile(email);
	return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponseDto.success("Succesfully fetched",response));
}

@PostMapping("/location")
public ResponseEntity<ApiResponseDto<String>>updateLocation(
		@RequestHeader ("Authorization")String authHeader,
		@RequestParam double latitude,
		@RequestParam double longitude){
String email=extractEmail(authHeader);
User user=userRepo.findByEmail(email).orElseThrow();
DriverLocation location=locationRepo.findByUserId(user.getId()).orElseThrow();
location.setLatitude(latitude);
location.setLongitude(longitude);
location.setUser(user);
location.setIsAvailable(true);
locationRepo.save(location);
return ResponseEntity.status(HttpStatus.OK)
		.body(ApiResponseDto.success("Updated location"));
}


@PostMapping("/availability")
public ResponseEntity<ApiResponseDto<String>>updateAvailability
(@RequestHeader ("Authorization")String authHeader,
@RequestParam boolean isAvailable){
	String email=extractEmail(authHeader);
	User user=userRepo.findByEmail(email).orElseThrow();
	DriverLocation location=locationRepo.findByUserId(user.getId()).orElseThrow();
	location.setIsAvailable(isAvailable);
	locationRepo.save(location);
	return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponseDto.success("updated availability",isAvailable?"online":"offline"));
}


@PutMapping("/approve{id}")
public ResponseEntity<ApiResponseDto<DriverProfileResponseDto>>approve
(@RequestHeader ("Authorization") String authHeader,
@PathVariable long id){
	String email=extractEmail(authHeader);
	DriverProfileResponseDto response=driverService.approveStatus(id);
	return ResponseEntity.status(HttpStatus.ACCEPTED)
			.body(ApiResponseDto.success("Driver profile Approved",response));
	
}


@PutMapping("/reject{id}")
public ResponseEntity<ApiResponseDto<DriverProfileResponseDto>>reject
(@RequestHeader ("Authorization")String authHeader,
@PathVariable long id){
	String email=extractEmail(authHeader);
	DriverProfileResponseDto response=driverService.rejectStatus(id);
	return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponseDto.success("Driver profile rejected",response));
}



}
