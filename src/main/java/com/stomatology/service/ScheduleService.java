package com.stomatology.service;

import com.stomatology.dto.ScheduleDto;
import com.stomatology.entity.Schedule;
import com.stomatology.mapper.ScheduleMapper;
import com.stomatology.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Schedule> save(List<ScheduleDto> schedulesDtoList) {
        List<Schedule> schedules = schedulesDtoList
                .stream()
                .map(scheduleMapper::toEntity)
                .collect(Collectors.toList());

        return scheduleRepository.saveAll(schedules);

    }
}
