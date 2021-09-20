package com.stomatology.service;

import com.stomatology.dto.FeedbackDto;
import com.stomatology.dto.create.CreateFeedbackDto;
import com.stomatology.entity.Feedback;
import com.stomatology.entity.user.Patient;
import com.stomatology.mapper.FeedbackMapper;
import com.stomatology.repository.FeedbackRepository;
import com.stomatology.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;
    private final PatientRepository patientRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public FeedbackDto createFeedback(CreateFeedbackDto createFeedbackDto) {
        Feedback feedback = new Feedback();
        feedback.setRating(createFeedbackDto.getRating());
        feedback.setText(createFeedbackDto.getText());
        Patient patient = patientRepository.getById(createFeedbackDto.getPatientId());
        feedback.setPatient(patient);
        return feedbackMapper.toDto(feedbackRepository.save(feedback));
    }

    public List<FeedbackDto> findTopLast() {
        return feedbackRepository.findFirst10ByOrderByIdDesc()
                .stream()
                .map(feedbackMapper::toDto)
                .collect(Collectors.toList());
    }
}
