package com.UberBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UberBackend.Entity.DriverProfile;
import com.UberBackend.Entity.User;
import com.UberBackend.Entity.Vehicle;
import com.UberBackend.dto.DriverProfileRequestDto;
import com.UberBackend.dto.DriverProfileResponseDto;
import com.UberBackend.enums.DriverStatus;
import com.UberBackend.enums.Role;
import com.UberBackend.exceptions.*;
import com.UberBackend.repository.DriverProfileRepository;
import com.UberBackend.repository.UserRepository;
import com.UberBackend.repository.VehicleRepository;

@Service
public class DriverService{
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private VehicleRepository vehicleRepo;
	@Autowired
	private DriverProfileRepository driverRepo;
	public DriverProfileResponseDto mapToDto(DriverProfile profile,Vehicle vehicle) {
	DriverProfileResponseDto dto=new DriverProfileResponseDto();
	dto.setColour(vehicle.getColour());
	dto.setEmail(profile.getUser().getEmail());
	dto.setId(profile.getId());
	dto.setLicenseNumber(profile.getLicense());
	dto.setModel(vehicle.getModel());
	dto.setName(profile.getUser().getName());
	dto.setPlateNumber(vehicle.getPlateNumber());
	dto.setRating(profile.getRating());
	dto.setStatus(profile.getStatus());
	dto.setTotalRides(profile.getTotalRides());
	dto.setVehicleType(vehicle.getVehicleType());
	return dto;
	}
	
	@Transactional
	public  DriverProfileResponseDto register(String email,DriverProfileRequestDto dto) {
		User user=userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User cant be found"));
		if(user.getRole()!=Role.USER) {
			throw new InvalidCredentialsException("Only Driver can create Driver's accound");
		}
		if(vehicleRepo.existsByPlateNumber(dto.getPlateNumber())){
			throw new DuplicateResourceException("Vehicle of This plate number is aldready used");
		}
		if(driverRepo.existsByUserId(user.getId())) {
			throw new DuplicateResourceException("Driver Id existing of this credential");
		}
		DriverProfile profile=new DriverProfile();
		profile.setUser(user);
		profile.setLicense(dto.getLicenseNumber());
		driverRepo.save(profile);
		Vehicle vehicle =new Vehicle();
		vehicle.setDriverProfile(profile);
		vehicle.setColour(dto.getColour());
		vehicle.setModel(dto.getModel());
		vehicle.setPlateNumber(dto.getPlateNumber());
		vehicle.setVehicleType(dto.getVehicleType());
		vehicleRepo.save(vehicle);
		
		return mapToDto(profile,vehicle);
	}
	
	@Transactional
	public  DriverProfileResponseDto getDriverProfile(String email) {
		User user=userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User cant be found"));
		DriverProfile profile=driverRepo.findByUserId(user.getId()).orElseThrow(()->
		new ResourceNotFoundException("Driver Profile cant be found"));
		Vehicle vehicle=vehicleRepo.findByDriverProfileId(profile.getId()).orElseThrow(()
				->new ResourceNotFoundException("Vehicle cant be found"));
		return mapToDto(profile,vehicle);
	}
	
	@Transactional
	public DriverProfileResponseDto approveStatus(Long id) {
		DriverProfile profile=driverRepo.findById(id).orElseThrow(()
				->new ResourceNotFoundException("Couldnot find DriverProfile"));
		profile.setStatus(DriverStatus.APPROVED);
		driverRepo.save(profile);
		Vehicle vehicle=vehicleRepo.findByDriverProfileId(profile.getId()).orElseThrow(()
				->new ResourceNotFoundException("Couldnot find the vehicle"));
		return mapToDto(profile,vehicle);
	}
	
	
	
	@Transactional
	public DriverProfileResponseDto rejectStatus(Long id) {
		DriverProfile profile=driverRepo.findById(id).orElseThrow(()
				->new ResourceNotFoundException("Couldnot find the driver profile"));
		profile.setStatus(DriverStatus.REJECTED);
		driverRepo.save(profile);
	Vehicle vehicle=vehicleRepo.findByDriverProfileId(profile.getId()).orElseThrow(()->
	new ResourceNotFoundException("Couldnot find the vehicle"));
	return mapToDto(profile,vehicle);
	
	}
}