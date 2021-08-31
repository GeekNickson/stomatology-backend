package com.stomatology.resource;

import com.stomatology.dto.AdminDto;
import com.stomatology.dto.create.CreateAdminDto;
import com.stomatology.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AdminResource {

    private final AdminService adminService;

    @GetMapping()
    public List<AdminDto> findAll() {
        return adminService.findAll();
    }

    @GetMapping("/{id}")
    public AdminDto findOne(@PathVariable Long id) {
        return adminService.findOne(id);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminDto create(@ModelAttribute CreateAdminDto createAdminDto) {
        return adminService.create(createAdminDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        adminService.delete(id);
    }
}
