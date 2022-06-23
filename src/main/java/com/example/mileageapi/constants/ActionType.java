package com.example.mileageapi.constants;

import com.example.mileageapi.config.aspect.mileagepoint.AbstractMileagePoint;
import com.example.mileageapi.config.aspect.mileagepoint.AddMileagePoint;
import com.example.mileageapi.config.aspect.mileagepoint.DeleteMileagePoint;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.List;

public enum ActionType {
  ADD {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto, MileageHistoryRepository mileageHistoryRepository) {
      return new AddMileagePoint(dto, mileageHistoryRepository);
    }
  },
  MOD {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto, MileageHistoryRepository mileageHistoryRepository) {
      return null;
    }
  },
  DELETE {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto, MileageHistoryRepository mileageHistoryRepository) {
      return new DeleteMileagePoint(dto, mileageHistoryRepository);
    }
  };

  ActionType() {}

  protected abstract AbstractMileagePoint getMileagePoint(
      EventDTO dto, MileageHistoryRepository mileageHistoryRepository);

  public void saveMileagePoints(EventDTO dto, MileageHistoryRepository mileageHistoryRepository) {
    List<MileageHistory> points = getMileagePoint(dto, mileageHistoryRepository).getPoints();
    mileageHistoryRepository.saveAll(points);
  }
}
