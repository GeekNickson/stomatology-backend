package com.stomatology.dto;

import lombok.Data;

@Data
public class FeedbackDto {
    private Long id;
    private String text;
    private int rating;
    private PatientDto patient;
}
