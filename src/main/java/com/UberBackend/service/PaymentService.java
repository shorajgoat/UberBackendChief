package com.UberBackend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UberBackend.Entity.Payment;
import com.UberBackend.Entity.Ride;
import com.UberBackend.Entity.User;
import com.UberBackend.Entity.Wallet;

import com.UberBackend.dto.PaymentResponseDto;

import com.UberBackend.enums.PaymentStatus;
import com.UberBackend.enums.RideStatus;

import com.UberBackend.exceptions.InvalidCredentialsException;
import com.UberBackend.exceptions.ResourceNotFoundException;

import com.UberBackend.repository.PaymentRepository;
import com.UberBackend.repository.RideRepository;
import com.UberBackend.repository.UserRepository;
import com.UberBackend.repository.WalletRepository;
import com.UberBackend.Entity.User;


@Service
public class PaymentService {
	@Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private RideRepository rideRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private WalletRepository walletRepo;

    // Top up wallet
    @Transactional
    public Double topUpWallet(String email, Double amount) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Wallet wallet = walletRepo.findByUserId(user.getId())
                .orElse(new Wallet());
        wallet.setUser(user);
        wallet.setBalance(wallet.getBalance() == null ?
                amount : wallet.getBalance() + amount);
        walletRepo.save(wallet);

        return wallet.getBalance();
    }

    // Get wallet balance
    public Double getBalance(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Wallet wallet = walletRepo.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        return wallet.getBalance();
    }

    // Process payment after ride completes
    @Transactional
    public PaymentResponseDto processPayment(String email, Long rideId) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Ride ride = rideRepo.findById(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("Ride not found"));

        if (ride.getRideStatus() != RideStatus.COMPLETED) {
            throw new InvalidCredentialsException("Ride is not completed yet");
        }

        if (!ride.getUser().getId().equals(user.getId())) {
            throw new InvalidCredentialsException("This is not your ride");
        }

        Wallet wallet = walletRepo.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        if (wallet.getBalance() < ride.getFare()) {
            throw new InvalidCredentialsException("Insufficient wallet balance");
        }

        wallet.setBalance(wallet.getBalance() - ride.getFare());
        walletRepo.save(wallet);

        Payment payment = new Payment();
        payment.setRide(ride);
        payment.setUser(user);
        payment.setAmount(ride.getFare());
        payment.setStatus(PaymentStatus.COMPLETED);
        paymentRepo.save(payment);

        return mapToDto(payment);
    }

    // Refund payment on cancellation
    @Transactional
    public PaymentResponseDto refundPayment(Long rideId) {
        Payment payment = paymentRepo.findByRideId(rideId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        Wallet wallet = walletRepo.findByUserId(payment.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));

        wallet.setBalance(wallet.getBalance() + payment.getAmount());
        walletRepo.save(wallet);

        payment.setStatus(PaymentStatus.REFUNDED);
        paymentRepo.save(payment);

        return mapToDto(payment);
    }

    // Payment history
    public List<PaymentResponseDto> getPaymentHistory(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return paymentRepo.findAllByUserIdOrderByCreatedAtDesc(user.getId())
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private PaymentResponseDto mapToDto(Payment payment) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(payment.getId());
        dto.setRideId(payment.getRide().getId());
        dto.setRiderName(payment.getUser().getName());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setCreatedAt(payment.getCreatedAt());
        return dto;
    }
}
