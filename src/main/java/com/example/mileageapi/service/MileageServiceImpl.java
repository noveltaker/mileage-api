package com.example.mileageapi.service;

import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.MileageHistoryInfo;
import com.example.mileageapi.service.dto.MileageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MileageServiceImpl implements MileageService {

  private final MileageRepository mileageRepository;

  private final MileageHistoryRepository mileageHistoryRepository;

  @Override
  @Transactional(readOnly = true)
  public Optional<MileageInfo> getMyMileage(UUID userId) {
    return mileageRepository.findByUserId(userId, MileageInfo.class);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<MileageHistoryInfo> getMyMileageHistories(UUID userId) {
    return mileageHistoryRepository.findByMileage_UserId(userId, MileageHistoryInfo.class);
  }
}
