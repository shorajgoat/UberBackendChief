package com.UberBackend.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.UberBackend.Entity.DriverLocation;
public interface DriverLocationRepository extends JpaRepository<DriverLocation,Long>{
	Optional<DriverLocation>findByUserId(Long id);
	@Query("SELECT dl FROM DriverLocation dl Where dl.isAvailabl=true" )
	List<DriverLocation>findAllAvailableDrivers();
	
}