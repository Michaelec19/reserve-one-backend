package com.reserveone.lanhua.modules.subscription.dto;

import com.reserveone.lanhua.modules.membership.dto.MembershipSummaryDTO;
import com.reserveone.lanhua.modules.subscription.entity.SubscriptionStatus;
import com.reserveone.lanhua.modules.user.dto.UserSummaryDTO;

import java.time.Instant;
import java.time.LocalDate;

public record SubscriptionResponseDTO(
        Long id,
        MembershipSummaryDTO membership,
        UserSummaryDTO user,
        LocalDate dateStart,
        LocalDate dateEnd,
        SubscriptionStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
