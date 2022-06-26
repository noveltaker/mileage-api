package com.example.mileageapi.constants;

import com.example.mileageapi.config.aspect.mileagepoint.AbstractMileagePoint;
import com.example.mileageapi.config.aspect.mileagepoint.AddMileagePoint;
import com.example.mileageapi.config.aspect.mileagepoint.DeleteMileagePoint;
import com.example.mileageapi.config.aspect.mileagepoint.ModMileagePoint;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.List;

public enum ActionType {
  ADD {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto,
        MileageHistoryRepository mileageHistoryRepository,
        MileageRepository mileageRepository) {
      return new AddMileagePoint(dto, mileageHistoryRepository, mileageRepository);
    }
  },
  MOD {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto,
        MileageHistoryRepository mileageHistoryRepository,
        MileageRepository mileageRepository) {
      return new ModMileagePoint(dto, mileageHistoryRepository, mileageRepository);
    }
  },
  DELETE {
    @Override
    protected AbstractMileagePoint getMileagePoint(
        EventDTO dto,
        MileageHistoryRepository mileageHistoryRepository,
        MileageRepository mileageRepository) {
      return new DeleteMileagePoint(dto, mileageHistoryRepository, mileageRepository);
    }
  };

  ActionType() {}

  protected abstract AbstractMileagePoint getMileagePoint(
      EventDTO dto,
      MileageHistoryRepository mileageHistoryRepository,
      MileageRepository mileageRepository);

  public void saveMileagePoints(
      EventDTO dto,
      MileageHistoryRepository mileageHistoryRepository,
      MileageRepository mileageRepository) {
    List<MileageHistory> points =
        getMileagePoint(dto, mileageHistoryRepository, mileageRepository).getPoints();
    mileageHistoryRepository.saveAll(points);
  }
}
