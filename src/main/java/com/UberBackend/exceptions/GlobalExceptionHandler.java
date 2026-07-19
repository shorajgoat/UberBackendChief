package com.UberBackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.UberBackend.dto.ApiResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApiResponseDto<Void>>handleduplicate(DuplicateResourceException ex){
		return  ResponseEntity.status(HttpStatus.CONFLICT)
				.body(ApiResponseDto.failure(ex.getMessage()));
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiResponseDto<Void>>handleinvalidcredentials(InvalidCredentialsException ex){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ApiResponseDto.failure(ex.getMessage()));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseDto<Void>>handlenotfound(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiResponseDto.failure(ex.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponseDto<Void>>handlegeneral(Exception ex){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponseDto.failure("SORRY SOME ISSUED HAS OCCURED"));
	}
}
