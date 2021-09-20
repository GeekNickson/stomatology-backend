package com.stomatology.mapper;

import com.stomatology.dto.FeedbackDto;
import com.stomatology.entity.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface FeedbackMapper extends EntityMapper<Feedback, FeedbackDto> {
    @Override
    Feedback toEntity(FeedbackDto dto);

    @Override
    FeedbackDto toDto(Feedback entity);
}
