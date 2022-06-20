package com.example.mileageapi.service;

import com.example.mileageapi.service.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    public EventDTO createdReview(EventDTO dto) {

        /**
         * review created logic
         */
        return dto;
    }
}
