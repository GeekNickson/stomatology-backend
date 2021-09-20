package com.stomatology.dto.create;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateFeedbackDto {
    @Size(min=10, max=1000)
    private String text;

    @Max(5)
    @Min(1)
    private Integer rating;

    private Long patientId;
}
