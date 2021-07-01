package com.stomatology.entity;

import com.stomatology.entity.user.Doctor;
import com.stomatology.entity.user.Patient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_appointment")
@Getter
@Setter
public class Appointment {

    @Id
    @SequenceGenerator(name = "appointmentSeq", sequenceName = "app_appointment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointmentSeq")
    private Long id;

    @Column(name = "time")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @NotNull
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @NotNull
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @NotNull
    private MedicalService service;
}
