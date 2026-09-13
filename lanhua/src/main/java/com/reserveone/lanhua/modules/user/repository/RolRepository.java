package com.reserveone.lanhua.modules.user.repository;

import com.reserveone.lanhua.modules.user.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNameRol(String nameRol);
}
