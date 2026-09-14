package com.reserveone.lanhua.modules.reservation.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reserveone.lanhua.modules.reservation.dto.ReservationRequestDto;
import com.reserveone.lanhua.modules.reservation.dto.ReservationResponseDto;
import com.reserveone.lanhua.modules.reservation.entity.Reservation;
import com.reserveone.lanhua.modules.reservation.repository.ReservationRepository;
import com.reserveone.lanhua.modules.schedule.entity.Schedule;
import com.reserveone.lanhua.modules.schedule.repository.ScheduleRepository;
import com.reserveone.lanhua.modules.user.entity.User;
import com.reserveone.lanhua.modules.user.repository.UserRepository;

@Service
public class ReservationService {

    private static final String STATE_PENDING = "PENDIENTE";
    private static final String STATE_CANCELLED = "CANCELADA";

    private final ReservationRepository reservationRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              ScheduleRepository scheduleRepository,
                              UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public List<ReservationResponseDto> listReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ReservationResponseDto> listByUser(Long idUser) {
        return reservationRepository.findByUsers_IdUser(idUser)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ReservationResponseDto getReservationById(Long id) {
        return mapToResponse(findReservationOrThrow(id));
    }

    public ReservationResponseDto createReservation(ReservationRequestDto dto) {
        List<User> users = userRepository.findAllById(dto.getIdUsers());
        if (users.isEmpty()) {
            throw new RuntimeException("No se encontraron usuarios válidos para la reserva");
        }

        Schedule schedule = scheduleRepository.findById(dto.getIdSchedule())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        if (schedule.getQuotas() == null || schedule.getQuotas() < users.size()) {
            throw new RuntimeException("No hay suficientes cupos disponibles para este horario");
        }

        schedule.setQuotas(schedule.getQuotas() - users.size());
        scheduleRepository.save(schedule);

        Reservation reservation = new Reservation();
        reservation.setUsers(users);
        reservation.setSchedule(schedule);
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setReservationState(STATE_PENDING);

        Reservation saved = reservationRepository.save(reservation);
        return mapToResponse(saved);
    }

    public ReservationResponseDto cancelReservation(Long id) {
        Reservation reservation = findReservationOrThrow(id);

        if (STATE_CANCELLED.equals(reservation.getReservationState())) {
            throw new RuntimeException("La reserva ya estaba cancelada");
        }

        reservation.setReservationState(STATE_CANCELLED);

        Schedule schedule = reservation.getSchedule();
        schedule.setQuotas(schedule.getQuotas() + reservation.getUsers().size());
        scheduleRepository.save(schedule);

        Reservation updated = reservationRepository.save(reservation);
        return mapToResponse(updated);
    }

    private Reservation findReservationOrThrow(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }


    private ReservationResponseDto mapToResponse(Reservation reservation) {
        ReservationResponseDto response = new ReservationResponseDto();
        response.setIdReservation(reservation.getIdReservation());
        response.setIdUsers(reservation.getUsers().stream().map(User::getIdUser).collect(Collectors.toList()));
        response.setUserNames(reservation.getUsers().stream()
                .map(u -> u.getNameUser() + " " + u.getLastNameUser())
                .collect(Collectors.toList()));

        response.setIdSchedule(reservation.getSchedule().getIdSchedule());
        response.setModality(reservation.getSchedule().getModality());
        response.setReservationDate(reservation.getReservationDate());
        response.setReservationState(reservation.getReservationState());
        response.setCreatedAt(reservation.getCreatedAt());
        return response;
    }

    public void confirmUserReservations(Long idUser) {
        List<Reservation> userReservations = reservationRepository.findByUsers_IdUser(idUser);

        for (Reservation res : userReservations) {
            if ("PENDIENTE".equalsIgnoreCase(res.getReservationState()) || "PENDING".equalsIgnoreCase(res.getReservationState())) {
                res.setReservationState("CONFIRMED");
                reservationRepository.save(res);
            }
        }
    }
}