package com.example.mileageapi.service;

import com.example.mileageapi.service.dto.EventDTO;
import com.example.mileageapi.service.event.MileageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public EventDTO createdReview(EventDTO dto) {

        /**
         * review created logic
         */

        // evnent 추가
        MileageEvent mileageEvent = new MileageEvent(this, dto);

        applicationEventPublisher.publishEvent(mileageEvent);

        return dto;
    }
}
