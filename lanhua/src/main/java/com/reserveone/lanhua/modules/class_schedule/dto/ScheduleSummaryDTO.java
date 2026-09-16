package com.reserveone.lanhua.modules.class_schedule.dto;

import java.time.LocalDateTime;

public record ScheduleSummaryDTO(
        Long idSchedule,
        Integer quotas,
        LocalDateTime scheduleDate,
        String location,
        String image
) {
}