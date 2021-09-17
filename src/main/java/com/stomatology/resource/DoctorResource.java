package com.stomatology.resource;

import com.stomatology.dto.DoctorDto;
import com.stomatology.dto.create.CreateDoctorDto;
import com.stomatology.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoctorResource {

    private final DoctorService doctorService;

    @GetMapping("/public/doctors")
    public List<DoctorDto> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/public/doctors/{id}")
    public DoctorDto findOne(@PathVariable Long id) {
        return doctorService.findOne(id);
    }

    @GetMapping("/doctors/services/{serviceId}")
    public List<DoctorDto> findByService(@PathVariable Long serviceId) {
        return doctorService.findDoctorsByService(serviceId);
    }

    @PostMapping(path = "/doctors", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorDto create(@RequestPart("profilePicture") MultipartFile file, @RequestPart("doctor") CreateDoctorDto doctorDto) {
        doctorDto.setProfilePicture(file);
        return doctorService.create(doctorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.delete(id);
    }
}
