package com.UberBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UberBackend.service.UserService;
import com.UberBackend.utils.JwtUtil;



import com.UberBackend.Entity.User;
import com.UberBackend.dto.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
@Autowired
private JwtUtil jwtUtil;
@Autowired
private UserService userService;

@PostMapping("/signup")
public ResponseEntity<ApiResponseDto<String>>signup(@RequestBody UserRequestDto dto){
	User user=userService.Signup(dto);
	return  ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponseDto.success("Successfully created the id ","ID:"+user.getId()));
}
@PostMapping("/login")
public ResponseEntity<ApiResponseDto<String>>login(@RequestBody LoginRequestDto dto){
	User user=userService.login(dto);
	String token=jwtUtil.generateToken(user.getEmail(),user.getRole().name() );
	return ResponseEntity.ok(ApiResponseDto.success("Successfully logged in", token));
}

	
	
	
	
}
