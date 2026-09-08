package com.reserveone.lanhua.modules.user.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.reserveone.lanhua.modules.user.dto.UserRequestDto;
import com.reserveone.lanhua.modules.user.dto.UserResponseDto;
import com.reserveone.lanhua.modules.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userDto) {
        UserResponseDto newUser = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}