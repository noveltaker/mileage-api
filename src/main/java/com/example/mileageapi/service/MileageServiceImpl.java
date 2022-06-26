package com.example.mileageapi.service;

import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.MyMileage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MileageServiceImpl implements MileageService {

  private final MileageRepository mileageRepository;

  @Override
  @Transactional(readOnly = true)
  public Optional<MyMileage> getMyMileage(UUID userId) {
    return mileageRepository.findByUserId(userId, MyMileage.class);
  }
}
