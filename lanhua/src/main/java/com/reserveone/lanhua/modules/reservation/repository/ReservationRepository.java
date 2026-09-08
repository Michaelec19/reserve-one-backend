package com.reserveone.lanhua.modules.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reserveone.lanhua.modules.reservation.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser_IdUser(Long idUser);

    List<Reservation> findBySchedule_IdSchedule(Long idSchedule);
}