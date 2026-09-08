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
        return reservationRepository.findByUser_IdUser(idUser)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ReservationResponseDto getReservationById(Long id) {
        return mapToResponse(findReservationOrThrow(id));
    }

    public ReservationResponseDto createReservation(ReservationRequestDto dto) {
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Schedule schedule = scheduleRepository.findById(dto.getIdSchedule())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        if (schedule.getQuotas() == null || schedule.getQuotas() <= 0) {
            throw new RuntimeException("No hay cupos disponibles para este horario");
        }

        schedule.setQuotas(schedule.getQuotas() - 1);
        scheduleRepository.save(schedule);

        Reservation reservation = new Reservation();
        reservation.setUser(user);
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
        schedule.setQuotas(schedule.getQuotas() + 1);
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
        response.setIdUser(reservation.getUser().getIdUser());
        response.setUserName(reservation.getUser().getNameUser() + " " + reservation.getUser().getLastNameUser());
        response.setIdSchedule(reservation.getSchedule().getIdSchedule());
        response.setModality(reservation.getSchedule().getModality());
        response.setReservationDate(reservation.getReservationDate());
        response.setReservationState(reservation.getReservationState());
        response.setCreatedAt(reservation.getCreatedAt());
        return response;
    }
}