package com.UberBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UberBackend.dto.ApiResponseDto;
import com.UberBackend.dto.PaymentResponseDto;
import com.UberBackend.service.PaymentService;
import com.UberBackend.utils.JwtUtil;


@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
	

	    @Autowired
	    private PaymentService paymentService;

	    @Autowired
	    private JwtUtil jwtUtil;

	    // Top up wallet
	    @PostMapping("/topup")
	    public ResponseEntity<ApiResponseDto<Double>> topUp(
	            @RequestHeader("Authorization") String authHeader,
	            @RequestParam Double amount) {
	        String email = extractEmail(authHeader);
	        Double balance = paymentService.topUpWallet(email, amount);
	        return ResponseEntity.ok(ApiResponseDto.success("Wallet topped up", balance));
	    }

	    // Get wallet balance
	    @GetMapping("/balance")
	    public ResponseEntity<ApiResponseDto<Double>> getBalance(
	            @RequestHeader("Authorization") String authHeader) {
	        String email = extractEmail(authHeader);
	        Double balance = paymentService.getBalance(email);
	        return ResponseEntity.ok(ApiResponseDto.success("Wallet balance", balance));
	    }

	    // Process payment
	    @PostMapping("/pay/{rideId}")
	    public ResponseEntity<ApiResponseDto<PaymentResponseDto>> processPayment(
	            @RequestHeader("Authorization") String authHeader,
	            @PathVariable Long rideId) {
	        String email = extractEmail(authHeader);
	        PaymentResponseDto response = paymentService.processPayment(email, rideId);
	        return ResponseEntity.ok(ApiResponseDto.success("Payment successful", response));
	    }

	    // Refund payment
	    @PostMapping("/refund/{rideId}")
	    public ResponseEntity<ApiResponseDto<PaymentResponseDto>> refundPayment(
	            @PathVariable Long rideId) {
	        PaymentResponseDto response = paymentService.refundPayment(rideId);
	        return ResponseEntity.ok(ApiResponseDto.success("Payment refunded", response));
	    }

	    // Payment history
	    @GetMapping("/history")
	    public ResponseEntity<ApiResponseDto<List<PaymentResponseDto>>> getHistory(
	            @RequestHeader("Authorization") String authHeader) {
	        String email = extractEmail(authHeader);
	        List<PaymentResponseDto> response = paymentService.getPaymentHistory(email);
	        return ResponseEntity.ok(ApiResponseDto.success("Payment history", response));
	    }

	    private String extractEmail(String authHeader) {
	        String token = authHeader.substring(7);
	        return jwtUtil.extractEmail(token);
	    }
}


