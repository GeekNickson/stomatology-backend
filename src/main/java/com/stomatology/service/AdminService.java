package com.stomatology.service;

import com.stomatology.dto.AdminDto;
import com.stomatology.dto.create.CreateAdminDto;
import com.stomatology.entity.enums.RoleName;
import com.stomatology.entity.user.Admin;
import com.stomatology.mapper.AdminMapper;
import com.stomatology.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final UserService userService;

    public AdminDto create(CreateAdminDto createAdminDto) {
        Admin admin = new Admin();
        admin.setUser(userService.create(createAdminDto, RoleName.ADMIN));
        return adminMapper.toDto(adminRepository.save(admin));
    }

    public List<AdminDto> findAll() {
        return adminRepository.findAll()
                .stream()
                .map(adminMapper::toDto)
                .collect(Collectors.toList());
    }

    public AdminDto findOne(Long id) {
        return adminRepository.findById(id)
                .map(adminMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));
    }

    public void delete(Long id) {
        adminRepository.deleteById(id);
    }
}
