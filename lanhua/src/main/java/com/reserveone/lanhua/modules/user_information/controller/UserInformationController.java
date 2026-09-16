package com.reserveone.lanhua.modules.user_information.controller;

import com.reserveone.lanhua.modules.user_information.dto.UserInformationDTO;
import com.reserveone.lanhua.modules.user_information.service.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-information")
public class UserInformationController {

    @Autowired
    private UserInformationService service;

    @GetMapping("/{idUser}")
    public ResponseEntity<UserInformationDTO> getByUserId(@PathVariable Integer idUser) {
        UserInformationDTO info = service.getByUserId(idUser);
        if (info != null) {
            return ResponseEntity.ok(info);
        }
        // Devuelve 204 No Content si el usuario existe pero no tiene perfil médico aún
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserInformationDTO> saveOrUpdate(@RequestBody UserInformationDTO dto) {
        UserInformationDTO saved = service.saveOrUpdate(dto);
        return ResponseEntity.ok(saved);
    }
}