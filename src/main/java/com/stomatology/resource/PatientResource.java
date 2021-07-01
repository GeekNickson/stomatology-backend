package com.stomatology.resource;

import com.stomatology.dto.PatientDto;
import com.stomatology.dto.create.CreatePatientDto;
import com.stomatology.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientResource {

    private final PatientService patientService;

    @GetMapping()
    public List<PatientDto> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public PatientDto findOne(@PathVariable Long id) {
        return patientService.findOne(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PatientDto create(@ModelAttribute CreatePatientDto patientDto) throws URISyntaxException, IOException {
        return patientService.create(patientDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }
}
