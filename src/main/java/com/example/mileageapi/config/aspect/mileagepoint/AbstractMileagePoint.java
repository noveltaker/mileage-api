package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

public abstract class AbstractMileagePoint implements Point {
  private final EventDTO dto;

  private final MileageRepository mileageRepository;

  private final MileageHistoryRepository mileageHistoryRepository;

  protected AbstractMileagePoint(
      EventDTO dto,
      MileageRepository mileageRepository,
      MileageHistoryRepository mileageHistoryRepository) {
    this.dto = dto;
    this.mileageRepository = mileageRepository;
    this.mileageHistoryRepository = mileageHistoryRepository;
  }

  protected final EventDTO getDto() {
    return dto;
  }

  protected final MileageHistoryRepository getMileageHistoryRepository() {
    return mileageHistoryRepository;
  }

  protected final MileageRepository getMileageRepository() {
    return mileageRepository;
  }
}
