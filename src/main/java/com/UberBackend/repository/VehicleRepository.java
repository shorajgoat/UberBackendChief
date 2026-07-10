package com.UberBackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UberBackend.Entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Long>{
	Optional<Vehicle>findByDriverProfileId(Long id);
	boolean existsByPlateNumber(String plateNumber);
}
