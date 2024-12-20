package com.example.scheduleapp.service;

import com.example.scheduleapp.dto.ScheduleRequest;
import com.example.scheduleapp.dto.ScheduleResponse;
import com.example.scheduleapp.entity.Schedule;
import com.example.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements com.example.scheduleapp.service.ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponse createSchedule(ScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.setTitle(request.getTitle());
        schedule.setContent(request.getContent());
        schedule = scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt().toString());
    }

    @Override
    public List<ScheduleResponse> getSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(schedule -> new ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponse getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        return new ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt().toString());
    }

    @Override
    public ScheduleResponse updateSchedule(Long id, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow();
        schedule.setTitle(request.getTitle());
        schedule.setContent(request.getContent());
        schedule = scheduleRepository.save(schedule);
        return new ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt().toString());
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}