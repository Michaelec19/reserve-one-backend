package com.reserveone.lanhua.modules.reservation.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ReservationRequestDto {

    @NotEmpty(message = "Debe incluir al menos un usuario en la reserva")
    private List<Long> idUsers;

    @NotNull
    private Long idSchedule;
}