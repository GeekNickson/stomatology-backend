package com.stomatology.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_specialty")
@Getter
@Setter
public class Specialty {
    @Id
    @SequenceGenerator(name = "specialtySeq", sequenceName = "app_specialty_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialtySeq")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;
}
