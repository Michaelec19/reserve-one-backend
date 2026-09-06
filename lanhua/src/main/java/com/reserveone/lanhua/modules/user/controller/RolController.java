package com.reserveone.lanhua.modules.user.controller;

import com.reserveone.lanhua.modules.user.entity.Rol;
import com.reserveone.lanhua.modules.user.repository.RolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolRepository rolRepository;

    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // Endpoint para crear un rol: POST /api/roles
    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol newRol = rolRepository.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRol);
    }

    // Endpoint para listar roles: GET /api/roles
    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        return ResponseEntity.ok(rolRepository.findAll());
    }
}