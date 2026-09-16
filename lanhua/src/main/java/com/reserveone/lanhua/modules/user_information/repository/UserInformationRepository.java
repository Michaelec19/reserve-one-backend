package com.reserveone.lanhua.modules.user_information.repository;

import com.reserveone.lanhua.modules.user_information.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {

    @Query("SELECT u FROM UserInformation u WHERE u.idUser = :idUser")
    Optional<UserInformation> findByIdUser(@Param("idUser") Integer idUser);
}