package com.stomatology.resource;

import com.stomatology.dto.SpecialtyDto;
import com.stomatology.service.SpecialtyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
@RequiredArgsConstructor
public class SpecialtyResource {
    private final SpecialtyService specialtyService;

    @GetMapping()
    public List<SpecialtyDto> findAll() {
        return specialtyService.findAll();
    }

    @PostMapping()
    public SpecialtyDto create(@RequestBody SpecialtyDto specialtyDto) {
        return specialtyService.create(specialtyDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        specialtyService.delete(id);
    }

}
