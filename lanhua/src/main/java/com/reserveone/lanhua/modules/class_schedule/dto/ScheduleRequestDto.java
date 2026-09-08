package com.reserveone.lanhua.modules.schedule.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ScheduleRequestDto {

    @NotNull
    private Integer idCatalog;

    @NotBlank
    @Size(max = 20)
    private String modality;

    @NotBlank
    @Size(max = 20)
    private String level;

    @NotNull
    private Integer quotas;

    @NotNull
    private LocalDateTime scheduleDate;

    @Size(max = 100)
    private String location;

    private String image;

    @NotNull
    private Long idUser;
}