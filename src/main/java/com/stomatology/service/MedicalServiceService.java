package com.stomatology.service;


import com.stomatology.dto.ServiceDto;
import com.stomatology.dto.create.CreateServiceDto;
import com.stomatology.entity.MedicalService;
import com.stomatology.mapper.ServiceMapper;
import com.stomatology.repository.MedicalServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalServiceService {
    private final MedicalServiceRepository medicalServiceRepository;
    private final ImageService imageService;
    private final ServiceMapper serviceMapper;

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

    public void delete(Long id) {
        medicalServiceRepository.deleteById(id);
    }
}
