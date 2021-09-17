package com.stomatology.resource;

import com.stomatology.constants.RoleConstants;
import com.stomatology.dto.ServiceDto;
import com.stomatology.dto.create.CreateServiceDto;
import com.stomatology.service.MedicalServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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

    @GetMapping("/services/{id}")
    public ServiceDto findOne(@PathVariable Long id) {
        return medicalServiceService.findOne(id);
    }

    @GetMapping("/services/doctors/{doctorId}")
    public List<ServiceDto> findByDoctor(@PathVariable Long doctorId) {
        return medicalServiceService.findByDoctor(doctorId);
    }

    @PostMapping(value="/services", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({RoleConstants.ADMIN})
    public ServiceDto create(@ModelAttribute CreateServiceDto serviceDto) {
        return medicalServiceService.create(serviceDto);
    }

    @DeleteMapping("/services/{id}")
    @RolesAllowed(RoleConstants.ADMIN)
    public void delete(@PathVariable Long id) {
        medicalServiceService.delete(id);
    }
}
