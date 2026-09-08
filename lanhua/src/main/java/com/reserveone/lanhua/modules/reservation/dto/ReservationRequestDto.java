package com.reserveone.lanhua.modules.reservation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequestDto {

    @NotNull
    private Long idUser;

    @NotNull
    private Long idSchedule;
}