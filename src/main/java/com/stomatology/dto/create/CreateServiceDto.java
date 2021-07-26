package com.stomatology.dto.create;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class CreateServiceDto implements Serializable {
    private String name;
    private Double price;
    private MultipartFile image;
}
