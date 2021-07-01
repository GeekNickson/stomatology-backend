package com.stomatology.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "app_image")
@Getter
@Setter
public class Image {

    @Id
    @SequenceGenerator(name = "imageSeq", sequenceName = "app_image_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageSeq")
    private Long id;

    @Column(name = "path")
    @NotNull
    private String path;

    @Column(name = "name")
    @NotNull
    private String name;
}
