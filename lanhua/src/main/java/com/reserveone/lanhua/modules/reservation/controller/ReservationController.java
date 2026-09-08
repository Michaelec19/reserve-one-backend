package com.reserveone.lanhua.modules.reservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.reserveone.lanhua.modules.reservation.dto.ReservationRequestDto;
import com.reserveone.lanhua.modules.reservation.dto.ReservationResponseDto;
import com.reserveone.lanhua.modules.reservation.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        return ResponseEntity.ok(reservationService.listReservations());
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<ReservationResponseDto>> getReservationsByUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(reservationService.listByUser(idUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationRequestDto dto) {
        ReservationResponseDto created = reservationService.createReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponseDto> cancelReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.cancelReservation(id));
    }
}