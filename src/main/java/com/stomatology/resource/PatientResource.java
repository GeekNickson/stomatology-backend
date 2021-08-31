package com.stomatology.resource;

import com.stomatology.constants.RoleConstants;
import com.stomatology.dto.PatientDto;
import com.stomatology.dto.create.CreatePatientDto;
import com.stomatology.entity.enums.RoleName;
import com.stomatology.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientResource {

    private final PatientService patientService;
    
    @GetMapping()
    @RolesAllowed({RoleConstants.ADMIN, RoleConstants.DOCTOR})
    public List<PatientDto> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public PatientDto findOne(@PathVariable Long id) {
        return patientService.findOne(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PatientDto create(@ModelAttribute CreatePatientDto patientDto)  {
        return patientService.create(patientDto);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({RoleConstants.ADMIN})
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }
}
