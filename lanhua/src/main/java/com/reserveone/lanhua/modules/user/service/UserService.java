package com.reserveone.lanhua.modules.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByEmail(String emailUser) {
        return userRepository.findByEmailUser(emailUser);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
