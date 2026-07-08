package com.UberBackend.dto;

public class ApiResponseDto<T> {
	private String message;
	private boolean sucess;
	private T data;
	
	
	
public ApiResponseDto(String message, boolean sucess, T data) {
		this.message = message;
		this.sucess = sucess;
		this.data = data;
	}



public static <T>ApiResponseDto<T>success(String message,T data ){
	return new ApiResponseDto<>(message,true,data);
}
public static <T>ApiResponseDto<T>failure(String message){
	return new ApiResponseDto<>(message,false,null);
}
public static <T>ApiResponseDto<T>success(String message){
	return new ApiResponseDto<>(message,true,null);
}



public String getMessage() {
	return message;
}



public boolean isSucess() {
	return sucess;
}



public T getData() {
	return data;
}


}
