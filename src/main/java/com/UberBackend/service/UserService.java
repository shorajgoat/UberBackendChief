package com.UberBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.UberBackend.Entity.User;
import com.UberBackend.dto.LoginRequestDto;
import com.UberBackend.dto.UserRequestDto;
import com.UberBackend.exceptions.DuplicateResourceException;
import com.UberBackend.exceptions.ResourceNotFoundException;
import com.UberBackend.exceptions.InvalidCredentialsException;
import com.UberBackend.repository.UserRepository;

@Service
public class UserService {
@Autowired
private PasswordEncoder encoder;
@Autowired
private UserRepository userRepo;
public User Signup(UserRequestDto dto) {
	if(userRepo.existsByEmail(dto.getEmail())) {
		throw new DuplicateResourceException("Email Aldready in Use");
	}
	if(userRepo.existsByPhoneNo(dto.getPhoneNo()) ){
		throw new DuplicateResourceException("Phone no aldready in use");
	}
	User user=new User();
	user.setEmail(dto.getEmail());
	user.setName(dto.getName());
	user.setPhoneNo(dto.getPhoneNo());
	user.setRole(dto.getRole());
	user.setPassword(encoder.encode(dto.getPassword()));
	return userRepo.save(user);

}

public User login(LoginRequestDto dto) {
	User user;
	if(dto.getEmail()!=null&&!dto.getEmail().isBlank()) {
		user=userRepo.findByEmail(dto.getEmail()).orElseThrow(()->
		new ResourceNotFoundException("Couldnot find the email"));
	}
	else if(dto.getPhoneNo()!=null&&!dto.getPhoneNo().isBlank()) {
		
		user=userRepo.findByPhoneNo(dto.getPhoneNo()).orElseThrow(()->
		new ResourceNotFoundException("Couldnot find Phone no. "));
	}
		else {
			throw new InvalidCredentialsException("Enter the data");
		}
		if(!encoder.matches(dto.getPassword(),user.getPassword())){
			throw new InvalidCredentialsException("Enter correct password");
		}
		return user;
	}
}


