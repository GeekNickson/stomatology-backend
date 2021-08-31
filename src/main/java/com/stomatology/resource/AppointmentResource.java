package com.stomatology.resource;

import com.stomatology.dto.AppointmentDto;
import com.stomatology.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentResource {

    private final AppointmentService appointmentService;

    @GetMapping()
    public List<AppointmentDto> findAll() {
        return appointmentService.findAll();
    }

    @GetMapping("/{id}")
    public AppointmentDto findOne(@PathVariable Long id) {
        return appointmentService.findOne(id);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentDto> findByDoctor(@PathVariable Long doctorId) {
        return appointmentService.findByDoctor(doctorId);
    }

    @GetMapping("/patient/{patientId}")
    public List<AppointmentDto> findByPatient(@PathVariable Long patientId) {
        return appointmentService.findByPatient(patientId);
    }

    @PostMapping()
    public AppointmentDto create(AppointmentDto appointmentDto) {
        return appointmentService.create(appointmentDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        appointmentService.delete(id);
    }
}
