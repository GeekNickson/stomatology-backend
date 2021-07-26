package com.stomatology.resource;

import com.stomatology.dto.ServiceDto;
import com.stomatology.dto.create.CreateServiceDto;
import com.stomatology.service.MedicalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MedicalServiceResource {

    private final MedicalServiceService medicalServiceService;

    @GetMapping("/public/services")
    public List<ServiceDto> findAll() {
        return medicalServiceService.findAll();
    }

    @PostMapping(value="/services", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceDto create(@ModelAttribute CreateServiceDto serviceDto) {
        return medicalServiceService.create(serviceDto);
    }

    @DeleteMapping("/services/{id}")
    public void delete(@PathVariable Long id) {
        medicalServiceService.delete(id);
    }
}
