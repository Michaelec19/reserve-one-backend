package com.reserveone.lanhua.modules.schedule.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ScheduleResponseDto {
    private Long idSchedule;
    private Integer idCatalog;
    private String modality;
    private String level;
    private Integer quotas;
    private LocalDateTime scheduleDate;
    private String location;
    private String image;
    private Long idUser;
    private String userName;
    private LocalDateTime createdAt;
}