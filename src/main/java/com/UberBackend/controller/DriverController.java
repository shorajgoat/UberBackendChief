package com.UberBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UberBackend.dto.ApiResponseDto;
import com.UberBackend.dto.DriverProfileRequestDto;
import com.UberBackend.dto.DriverProfileResponseDto;
import com.UberBackend.repository.DriverProfileRepository;
import com.UberBackend.repository.VehicleRepository;
import com.UberBackend.service.DriverService;
import com.UberBackend.utils.JwtUtil;

@RestController
@RequestMapping("api/v1/drivers")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	@Autowired
	private JwtUtil util;
	@Autowired
	private DriverProfileRepository driverRepo;
	@Autowired
	private VehicleRepository vehicleRepo;
	
	public String ExtractEmail(String authHeader) {
		String token=authHeader.substring(7);
		return util.extractEmail(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponseDto<DriverProfileResponseDto>>register(
			@RequestHeader("Authoritation") String authHeader,
			@RequestBody DriverProfileRequestDto dto){
		String email=ExtractEmail(authHeader);
		DriverProfileResponseDto response=driverService.register(email, dto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponseDto.success("Successfully registered as a driver",response));
		
	}
	
	
	@PostMapping("/me")
	public ResponseEntity<ApiResponseDto<DriverProfileResponseDto>>getProfile(
			@RequestParam String email){
		DriverProfileResponseDto response=driverService.getDriverProfile(email);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ApiResponseDto.success("Successfully fetched",response));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
