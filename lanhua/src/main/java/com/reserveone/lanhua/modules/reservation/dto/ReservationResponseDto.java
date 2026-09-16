package com.reserveone.lanhua.modules.reservation.dto;

import java.time.LocalDateTime;

import com.reserveone.lanhua.modules.catalog.dto.CatalogSummaryDTO;
import com.reserveone.lanhua.modules.class_schedule.dto.ScheduleSummaryDTO;

public record ReservationResponseDto(
        Long idReservation,
        Long userId,
        ScheduleSummaryDTO schedule,
        CatalogSummaryDTO catalog,
        String modality,
        String reservationState,
        LocalDateTime reservationDate,
        LocalDateTime createdAt
) {}