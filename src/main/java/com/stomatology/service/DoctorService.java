package com.stomatology.service;

import com.stomatology.dto.DoctorDto;
import com.stomatology.dto.create.CreateDoctorDto;
import com.stomatology.entity.MedicalService;
import com.stomatology.entity.enums.RoleName;
import com.stomatology.entity.user.Doctor;
import com.stomatology.mapper.DoctorMapper;
import com.stomatology.mapper.ScheduleMapper;
import com.stomatology.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final UserService userService;
    private final ScheduleService scheduleService;
    private final MedicalServiceService medicalServiceService;
    private final SpecialtyService specialtyService;

    @Transactional
    public DoctorDto create(CreateDoctorDto createDoctorDto) {
        Doctor doctor = new Doctor();
        doctor.setExperience(createDoctorDto.getExperience());
        doctor.setPhoneNumber(createDoctorDto.getPhoneNumber());
        doctor.setUser(userService.create(createDoctorDto, RoleName.DOCTOR));
        doctor.setSpecialty(specialtyService.findOne(createDoctorDto.getSpecialtyId()));
        Set<MedicalService> services = medicalServiceService.findAll(createDoctorDto.getServicesIds());
        doctor.setServices(services);
        doctorRepository.save(doctor);
        doctor.setSchedules(scheduleService.save(createDoctorDto.getSchedules(), doctor));
        return doctorMapper.toDto(doctorRepository.save(doctor));
    }

    public List<DoctorDto> findAll() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    public DoctorDto findOne(Long id) {
        return doctorRepository.findById(id)
                .map(doctorMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
}
