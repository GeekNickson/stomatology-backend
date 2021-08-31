package com.stomatology.mapper;

import com.stomatology.dto.ScheduleDto;
import com.stomatology.entity.Schedule;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ScheduleMapper extends EntityMapper<Schedule, ScheduleDto> {

}
