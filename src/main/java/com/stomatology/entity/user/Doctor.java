package com.stomatology.entity.user;

import com.stomatology.entity.Appointment;
import com.stomatology.entity.MedicalService;
import com.stomatology.entity.Schedule;
import com.stomatology.entity.Specialty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "app_doctor")
@Getter
@Setter
public class Doctor {

    @Id
    private Long id;

    @Column(name = "experience")
    @NotNull
    private Integer experience;

    @Column(name = "phone")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    @MapsId
    private User user;

    @OneToOne
    @JoinColumn(name ="specialty_id", referencedColumnName = "id")
    private Specialty specialty;

    @OneToMany(mappedBy = "doctor")
    private List<MedicalService> services;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}
