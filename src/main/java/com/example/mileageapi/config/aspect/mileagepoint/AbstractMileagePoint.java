package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

public abstract class AbstractMileagePoint implements Point {
  private final EventDTO dto;

  private final MileageRepository mileageRepository;

  protected AbstractMileagePoint(EventDTO dto, MileageRepository mileageRepository) {
    this.dto = dto;
    this.mileageRepository = mileageRepository;
  }

  public final EventDTO getDto() {
    return dto;
  }

  public final MileageRepository getMileageRepository() {
    return mileageRepository;
  }
}
