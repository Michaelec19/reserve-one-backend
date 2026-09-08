package com.reserveone.lanhua.modules.user.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.reserveone.lanhua.modules.user.dto.UserRequestDto;
import com.reserveone.lanhua.modules.user.dto.UserResponseDto;
import com.reserveone.lanhua.modules.user.entity.Rol;
import com.reserveone.lanhua.modules.user.entity.User;
import com.reserveone.lanhua.modules.user.repository.RolRepository;
import com.reserveone.lanhua.modules.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;

    public UserService(UserRepository userRepository, RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
    }

    public List<UserResponseDto> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDto saveUser(UserRequestDto dto) {
        Rol rol = rolRepository.findById(dto.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        User user = new User();
        user.setNameUser(dto.getNameUser());
        user.setLastNameUser(dto.getLastNameUser());
        user.setEmailUser(dto.getEmailUser());
        user.setPasswordUser(dto.getPasswordUser());
        user.setRol(rol);

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    private UserResponseDto mapToResponse(User user) {
        UserResponseDto response = new UserResponseDto();
        response.setIdUser(user.getIdUser());
        response.setNameUser(user.getNameUser());
        response.setLastNameUser(user.getLastNameUser());
        response.setEmailUser(user.getEmailUser());
        response.setNameRol(user.getRol().getNameRol());
        response.setCreationDate(user.getCreationDate());
        return response;
    }
}