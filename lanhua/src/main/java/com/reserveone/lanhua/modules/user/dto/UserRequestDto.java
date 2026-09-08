package com.reserveone.lanhua.modules.user.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String nameUser;
    private String lastNameUser;
    private String emailUser;
    private String passwordUser;
    private Long idRol;
}