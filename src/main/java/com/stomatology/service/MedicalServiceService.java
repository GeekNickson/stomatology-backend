package com.stomatology.service;


import com.stomatology.dto.ServiceDto;
import com.stomatology.dto.create.CreateServiceDto;
import com.stomatology.entity.MedicalService;
import com.stomatology.entity.user.Doctor;
import com.stomatology.mapper.ServiceMapper;
import com.stomatology.repository.DoctorRepository;
import com.stomatology.repository.MedicalServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalServiceService {
    private final MedicalServiceRepository medicalServiceRepository;
    private final ImageService imageService;
    private final ServiceMapper serviceMapper;
    private final DoctorRepository doctorRepository;

    public ServiceDto create(CreateServiceDto createServiceDto) {
        MedicalService service = new MedicalService();
        service.setName(createServiceDto.getName());
        service.setPrice(BigDecimal.valueOf(createServiceDto.getPrice()));

        try {
            service.setImage(imageService.save(createServiceDto.getImage()));
        } catch (IOException e) {
            log.error("Error saving picture: {}", e.getMessage());
        }

        return serviceMapper.toDto(medicalServiceRepository.save(service));
    }

    public List<ServiceDto> findAll() {
        return medicalServiceRepository.findAll()
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    public ServiceDto findOne(Long id) {
        return medicalServiceRepository.findById(id)
                .map(serviceMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    public Set<MedicalService> findAll(Set<Long> ids) {
        Set<MedicalService> services = new HashSet<>();
        ids.forEach(id -> medicalServiceRepository.findById(id).ifPresent(services::add));
        return services;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, rollbackFor = ResponseStatusException.class)
    public List<ServiceDto> findByDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return medicalServiceRepository.findDistinctByDoctorsContaining(doctor)
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        medicalServiceRepository.deleteById(id);
    }
}
