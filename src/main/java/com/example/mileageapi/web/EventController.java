package com.example.mileageapi.web;

import com.example.mileageapi.service.EventService;
import com.example.mileageapi.service.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("events")
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO getReviewEvent(@RequestBody EventDTO dto) {
        return eventService.createdReview(dto);
    }
}
