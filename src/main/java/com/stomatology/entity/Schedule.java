package com.stomatology.entity;

import com.stomatology.entity.enums.Weekday;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "app_schedule")
@Getter
@Setter
public class Schedule {

    @Id
    @SequenceGenerator(name = "scheduleSeq", sequenceName = "app_schedule_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduleSeq")
    private Long id;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Weekday weekday;

    @Column(name = "start")
    @NotNull
    private LocalTime start;

    @Column(name = "end")
    @NotNull
    private LocalTime end;
}
