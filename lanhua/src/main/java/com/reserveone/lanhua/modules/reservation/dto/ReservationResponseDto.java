package com.reserveone.lanhua.modules.reservation.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ReservationResponseDto {
    private Long idReservation;
    private List<Long> idUsers;
    private List<String> userNames;
    private Long idSchedule;
    private String modality;
    private LocalDateTime reservationDate;
    private String reservationState;
    private LocalDateTime createdAt;
}