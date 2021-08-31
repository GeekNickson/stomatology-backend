package com.stomatology.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SpecialtyDto implements Serializable {

    private Long id;

    private String name;
}
