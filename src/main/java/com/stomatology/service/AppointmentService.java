package com.stomatology.service;

import com.stomatology.dto.AppointmentDto;
import com.stomatology.entity.Appointment;
import com.stomatology.entity.MedicalService;
import com.stomatology.entity.user.Doctor;
import com.stomatology.entity.user.Patient;
import com.stomatology.mapper.AppointmentMapper;
import com.stomatology.repository.AppointmentRepository;
import com.stomatology.repository.DoctorRepository;
import com.stomatology.repository.MedicalServiceRepository;
import com.stomatology.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final MedicalServiceRepository medicalServiceRepository;

    public AppointmentDto create(AppointmentDto appointmentDto) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDto);

        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        MedicalService service = medicalServiceRepository.findById(appointmentDto.getServiceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        appointment.setDoctor(doctor);
        appointment.setService(service);
        appointment.setPatient(patient);

        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public List<AppointmentDto> findAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto findOne(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<AppointmentDto> findByDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return appointmentRepository.findByDoctor(doctor)
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AppointmentDto> findByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return appointmentRepository.findByPatient(patient)
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
