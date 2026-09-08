package com.reserveone.lanhua.modules.subscription.dto;

import com.reserveone.lanhua.modules.subscription.entity.SubscriptionStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SubscriptionRequestDTO(
        @NotNull(message = "El id de la membresía es obligatorio")
        Long membershipId,

        @NotNull(message = "El id del usuario es obligatorio")
        Long userId,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate dateStart,

        @NotNull(message = "La fecha de fin es obligatoria")
        @FutureOrPresent(message = "La fecha de fin no puede ser en el pasado")
        LocalDate dateEnd,

        @NotNull(message = "El estado es obligatorio")
        SubscriptionStatus status
) {
}