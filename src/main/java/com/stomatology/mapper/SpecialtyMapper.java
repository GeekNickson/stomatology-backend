package com.stomatology.mapper;

import com.stomatology.dto.SpecialtyDto;
import com.stomatology.entity.Specialty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper extends EntityMapper<Specialty, SpecialtyDto> {

}
