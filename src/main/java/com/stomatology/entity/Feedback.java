package com.stomatology.entity;

import com.stomatology.entity.user.Patient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "app_feedback")
@Getter
@Setter
public class Feedback {

    @Id
    @SequenceGenerator(name = "feedbackSeq", sequenceName = "app_feedback_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedbackSeq")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
}
