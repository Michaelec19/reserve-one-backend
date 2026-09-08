package com.reserveone.lanhua.modules.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reserveone.lanhua.modules.schedule.dto.ScheduleRequestDto;
import com.reserveone.lanhua.modules.schedule.dto.ScheduleResponseDto;
import com.reserveone.lanhua.modules.schedule.entity.Schedule;
import com.reserveone.lanhua.modules.schedule.repository.ScheduleRepository;
import com.reserveone.lanhua.modules.user.entity.User;
import com.reserveone.lanhua.modules.user.repository.UserRepository;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public List<ScheduleResponseDto> listSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto getScheduleById(Long id) {
        Schedule schedule = findScheduleOrThrow(id);
        return mapToResponse(schedule);
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Schedule schedule = new Schedule();
        schedule.setIdCatalog(dto.getIdCatalog());
        schedule.setModality(dto.getModality());
        schedule.setLevel(dto.getLevel());
        schedule.setQuotas(dto.getQuotas());
        schedule.setScheduleDate(dto.getScheduleDate());
        schedule.setLocation(dto.getLocation());
        schedule.setImage(dto.getImage());
        schedule.setUser(user);

        Schedule saved = scheduleRepository.save(schedule);
        return mapToResponse(saved);
    }

    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = findScheduleOrThrow(id);

        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        schedule.setIdCatalog(dto.getIdCatalog());
        schedule.setModality(dto.getModality());
        schedule.setLevel(dto.getLevel());
        schedule.setQuotas(dto.getQuotas());
        schedule.setScheduleDate(dto.getScheduleDate());
        schedule.setLocation(dto.getLocation());
        schedule.setImage(dto.getImage());
        schedule.setUser(user);

        Schedule updated = scheduleRepository.save(schedule);
        return mapToResponse(updated);
    }

    public void deleteSchedule(Long id) {
        Schedule schedule = findScheduleOrThrow(id);
        scheduleRepository.delete(schedule);
    }

    private Schedule findScheduleOrThrow(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    private ScheduleResponseDto mapToResponse(Schedule schedule) {
        ScheduleResponseDto response = new ScheduleResponseDto();
        response.setIdSchedule(schedule.getIdSchedule());
        response.setIdCatalog(schedule.getIdCatalog());
        response.setModality(schedule.getModality());
        response.setLevel(schedule.getLevel());
        response.setQuotas(schedule.getQuotas());
        response.setScheduleDate(schedule.getScheduleDate());
        response.setLocation(schedule.getLocation());
        response.setImage(schedule.getImage());
        response.setIdUser(schedule.getUser().getIdUser());
        response.setUserName(schedule.getUser().getNameUser() + " " + schedule.getUser().getLastNameUser());
        response.setCreatedAt(schedule.getCreatedAt());
        return response;
    }
}