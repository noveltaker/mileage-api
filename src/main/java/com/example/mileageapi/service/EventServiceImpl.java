package com.example.mileageapi.service;

import com.example.mileageapi.service.dto.EventDTO;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public EventDTO createdReview(EventDTO dto) {
        return dto;
    }
}
