package com.reserveone.lanhua.modules.membership.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record MembershipResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Instant createdAt,
        Instant updatedAt
) {
}
