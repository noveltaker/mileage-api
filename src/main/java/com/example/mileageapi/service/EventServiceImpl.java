package com.example.mileageapi.service;

import com.example.mileageapi.service.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    @Override
    @Transactional
    public EventDTO createdReview(EventDTO dto) {
        // 리뷰 로직
        return dto;
    }
}
