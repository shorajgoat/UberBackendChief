package com.UberBackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.UberBackend.Entity.User;
import com.UberBackend.enums.Role;

public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByPhoneNo(String phoneNo);
	boolean existsByEmail(String email);
	boolean existsByPhoneNo(String phoneNo);
	List<User> findAllByRole(Role role);
}
