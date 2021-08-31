package com.stomatology.service;

import com.stomatology.dto.ScheduleDto;
import com.stomatology.entity.Schedule;
import com.stomatology.entity.user.Doctor;
import com.stomatology.mapper.ScheduleMapper;
import com.stomatology.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public Schedule save(ScheduleDto scheduleDto) {
        return scheduleRepository.save(scheduleMapper.toEntity(scheduleDto));
    }

    @Transactional
    public List<Schedule> save(List<ScheduleDto> schedulesDtoList, Doctor doctor) {
        List<Schedule> schedules = schedulesDtoList
                .stream()
                .map(scheduleMapper::toEntity)
                .collect(Collectors.toList());

        schedules.forEach(schedule -> schedule.setDoctor(doctor));
        return scheduleRepository.saveAll(schedules);
    }
}
