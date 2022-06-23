package com.example.mileageapi.constants;

import com.example.mileageapi.config.aspect.mileagepoint.AbstractMileagePoint;
import com.example.mileageapi.config.aspect.mileagepoint.AddMileagePoint;
import com.example.mileageapi.config.aspect.mileagepoint.DeleteMileagePoint;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.List;

public enum ActionType {
  ADD {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto, MileageRepository mileageRepository) {
      return new AddMileagePoint(dto, mileageRepository);
    }
  },
  MOD {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto, MileageRepository mileageRepository) {
      return null;
    }
  },
  DELETE {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto, MileageRepository mileageRepository) {
      return new DeleteMileagePoint(dto, mileageRepository);
    }
  };

  ActionType() {}

  protected abstract AbstractMileagePoint getMileagePoint(
      EventDTO dto, MileageRepository mileageRepository);

  public void saveMileagePoints(EventDTO dto, MileageRepository mileageRepository) {
    List<Mileage> points = getMileagePoint(dto, mileageRepository).getPoints();
    mileageRepository.saveAll(points);
  }
}
