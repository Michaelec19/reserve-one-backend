package com.reserveone.lanhua.modules.class_schedule.dto;

import java.time.LocalDateTime;
import com.reserveone.lanhua.modules.catalog.dto.CatalogSummaryDTO;

import com.reserveone.lanhua.modules.user.dto.UserSummaryDTO;

public record ScheduleResponseDto(
        Long idSchedule,
        CatalogSummaryDTO catalog,
        String modality,
        String level,
        Integer quotas,
        LocalDateTime scheduleDate,
        String location,
        String image,
        UserSummaryDTO user,
        LocalDateTime createdAt
) {
}