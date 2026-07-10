package com.UberBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UberBackend.Entity.DriverProfile;
import com.UberBackend.enums.DriverStatus;

public interface DriverProfileRepository extends JpaRepository<DriverProfile,Long> {
	Optional<DriverProfile>findByUserId(Long id);
	List<DriverProfile>findAllByStatus(DriverStatus status);
	boolean existsByUserId(long id);

}
