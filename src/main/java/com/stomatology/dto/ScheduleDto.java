package com.stomatology.dto;

import com.stomatology.entity.enums.Weekday;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;


@Getter
@Setter
public class ScheduleDto implements Serializable {
    private Long id;

    private Weekday weekday;

    private LocalTime start;

    private LocalTime end;
}
