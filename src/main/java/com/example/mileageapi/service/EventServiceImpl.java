package com.example.mileageapi.service;

import com.example.mileageapi.repository.ReviewRepository;
import com.example.mileageapi.service.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public EventDTO createdReview(EventDTO dto) {
        reviewRepository.save(dto.toEntity());
        return dto;
    }
}
