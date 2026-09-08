package com.reserveone.lanhua.modules.reservation.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReservationResponseDto {
    private Long idReservation;
    private Long idUser;
    private String userName;
    private Long idSchedule;
    private String modality;
    private LocalDateTime reservationDate;
    private String reservationState;
    private LocalDateTime createdAt;
}