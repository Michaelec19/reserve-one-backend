package com.reserveone.lanhua.modules.auth.service;

import com.reserveone.lanhua.modules.auth.dto.LoginDTO;
import com.reserveone.lanhua.modules.user.entity.User;
import com.reserveone.lanhua.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(LoginDTO loginDto) {
        Optional<User> userOptional = userRepository.findByEmailUser(loginDto.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(loginDto.getPassword(), user.getPasswordUser());
        }
        return false;
    }
}
