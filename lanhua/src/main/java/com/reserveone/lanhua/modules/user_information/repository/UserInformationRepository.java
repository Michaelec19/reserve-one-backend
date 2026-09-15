package com.reserveone.lanhua.modules.user_information.repository;

import com.reserveone.lanhua.modules.user_information.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {

    // Cambiamos "Id" por "IdUser" y el tipo de dato a Long
    Optional<UserInformation> findByUser_IdUser(Long idUser);
}