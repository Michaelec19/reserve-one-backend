package com.reserveone.lanhua.modules.user.repository;

import com.reserveone.lanhua.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailUser(String emailUser);
}