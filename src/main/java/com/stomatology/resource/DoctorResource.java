package com.stomatology.resource;

import com.stomatology.dto.DoctorDto;
import com.stomatology.dto.create.CreateDoctorDto;
import com.stomatology.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorResource {

    private final DoctorService doctorService;

    @GetMapping()
    public List<DoctorDto> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public DoctorDto findOne(@PathVariable Long id) {
        return doctorService.findOne(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorDto create(@ModelAttribute CreateDoctorDto doctorDto) {
        return doctorService.create(doctorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}
