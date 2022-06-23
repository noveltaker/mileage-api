package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.service.dto.EventDTO;

public abstract class AbstractMileagePoint implements Point {
  private final EventDTO dto;

  private final MileageHistoryRepository mileageHistoryRepository;

  protected AbstractMileagePoint(EventDTO dto, MileageHistoryRepository mileageHistoryRepository) {
    this.dto = dto;
    this.mileageHistoryRepository = mileageHistoryRepository;
  }

  public final EventDTO getDto() {
    return dto;
  }

  public final MileageHistoryRepository getMileageRepository() {
    return mileageHistoryRepository;
  }
}
