package com.reserveone.lanhua.modules.schedule.dto;

import java.time.LocalDateTime;
import com.reserveone.lanhua.modules.catalog.dto.CatalogSummaryDTO; // Importas el DTO que acabamos de crear
import lombok.Data;

@Data
public class ScheduleResponseDto {
    private Long idSchedule;
    private CatalogSummaryDTO catalog;
    private String modality;
    private String level;
    private Integer quotas;
    private LocalDateTime scheduleDate;
    private String location;
    private Long idUser;
    private String userName;
    private LocalDateTime createdAt;
}