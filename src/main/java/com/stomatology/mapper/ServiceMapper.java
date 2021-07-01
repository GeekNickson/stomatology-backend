package com.stomatology.mapper;

import com.stomatology.dto.ServiceDto;
import com.stomatology.entity.MedicalService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ServiceMapper extends EntityMapper<MedicalService, ServiceDto> {

    @Mapping(target = "imageUrl", source = "image.path")
    @Override
    ServiceDto toDto(MedicalService entity);
}
