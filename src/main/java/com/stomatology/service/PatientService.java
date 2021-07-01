package com.stomatology.service;

import com.stomatology.dto.PatientDto;
import com.stomatology.dto.create.CreatePatientDto;
import com.stomatology.entity.enums.RoleName;
import com.stomatology.entity.user.Patient;
import com.stomatology.mapper.PatientMapper;
import com.stomatology.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final UserService userService;

    public PatientDto create(CreatePatientDto createPatientDto) {
        Patient patient = new Patient();
        patient.setPhoneNumber(createPatientDto.getPhoneNumber());
        patient.setUser(userService.create(createPatientDto, RoleName.USER));
        return patientMapper.toDto(patientRepository.save(patient));
    }

    public List<PatientDto> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDto findOne(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found"));
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}
