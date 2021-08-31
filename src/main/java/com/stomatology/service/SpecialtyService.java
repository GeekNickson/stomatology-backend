package com.stomatology.service;

import com.stomatology.dto.SpecialtyDto;
import com.stomatology.entity.Specialty;
import com.stomatology.mapper.SpecialtyMapper;
import com.stomatology.repository.SpecialtyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyService {
    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    public SpecialtyDto create(SpecialtyDto specialtyDto) {
        return specialtyMapper.toDto(specialtyRepository.save(specialtyMapper.toEntity(specialtyDto)));
    }

    public List<SpecialtyDto> findAll() {
        return specialtyRepository.findAll()
                .stream()
                .map(specialtyMapper::toDto)
                .collect(Collectors.toList());
    }

    public Specialty findOne(Long id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(Long id) {
        specialtyRepository.deleteById(id);
    }
}
