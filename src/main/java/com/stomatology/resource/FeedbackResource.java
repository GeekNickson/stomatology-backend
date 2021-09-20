package com.stomatology.resource;

import com.stomatology.dto.FeedbackDto;
import com.stomatology.dto.create.CreateFeedbackDto;
import com.stomatology.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FeedbackResource {

    private final FeedbackService feedbackService;

    @GetMapping("/public/feedback")
    public List<FeedbackDto> findLast() {
        return feedbackService.findTopLast();
    }

    @PostMapping("/feedback")
    public FeedbackDto create(@RequestBody @Valid CreateFeedbackDto feedbackDto) {
        return feedbackService.createFeedback(feedbackDto);
    }
}
