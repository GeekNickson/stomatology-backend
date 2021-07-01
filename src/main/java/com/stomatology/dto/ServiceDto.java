package com.stomatology.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class ServiceDto implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
}
