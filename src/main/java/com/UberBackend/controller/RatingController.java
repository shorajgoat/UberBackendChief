package com.UberBackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.UberBackend.dto.ApiResponseDto;
import com.UberBackend.dto.RatingRequestDto;
import com.UberBackend.dto.RatingResponseDto;
import com.UberBackend.service.RatingService;
import com.UberBackend.utils.JwtUtil;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private JwtUtil jwtUtil;

    // Submit rating after ride
    @PostMapping("/submit")
    public ResponseEntity<ApiResponseDto<RatingResponseDto>> submitRating(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody RatingRequestDto dto) {
        String email = extractEmail(authHeader);
        RatingResponseDto response = ratingService.submitRating(email, dto);
        return ResponseEntity.ok(ApiResponseDto.success("Rating submitted", response));
    }

    // Get driver average rating
    @GetMapping("/driver/{driverUserId}")
    public ResponseEntity<ApiResponseDto<Double>> getDriverRating(
            @PathVariable Long driverUserId) {
        Double rating = ratingService.getDriverAverageRating(driverUserId);
        return ResponseEntity.ok(ApiResponseDto.success("Driver rating", rating));
    }

    private String extractEmail(String authHeader) {
        String token = authHeader.substring(7);
        return jwtUtil.extractEmail(token);
    }
}