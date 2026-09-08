package com.reserveone.lanhua.modules.user.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponseDto {
    private Long idUser;
    private String nameUser;
    private String lastNameUser;
    private String emailUser;
    private String nameRol;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}