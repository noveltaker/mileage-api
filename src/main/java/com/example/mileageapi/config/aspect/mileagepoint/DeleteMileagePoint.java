package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.config.exception.NotMatchMileageTypeException;
import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeleteMileagePoint extends AbstractMileagePoint {

  private final List<MileageHistoryType> mileageHistoryTypeList =
      List.of(
          MileageHistoryType.CONTENT_ADD,
          MileageHistoryType.PHOTO_ADD,
          MileageHistoryType.REVIEW_ADD);

  public DeleteMileagePoint(
      EventDTO dto,
      MileageHistoryRepository mileageHistoryRepository,
      MileageRepository mileageRepository) {
    super(dto, mileageRepository, mileageHistoryRepository);
  }

  @Override
  public List<MileageHistory> getPoints() {

    EventDTO dto = this.getDto();

    MileageHistoryRepository mileageHistoryRepository = getMileageHistoryRepository();

    UUID reviewId = dto.getReviewId();

    List<MileageHistory> reviewMileageList = mileageHistoryRepository.findByReviewId(reviewId);

    List<MileageHistory> deleteMileageList = new ArrayList<>();

    for (MileageHistoryType type : mileageHistoryTypeList) {

      for (MileageHistory mileage : reviewMileageList) {
        if (type.equals(mileage.getType())) {

          MileageHistoryType removeMileageHistoryType = changeRemoveType(mileage.getType());

          int point = mileage.getPoint() * -1;

          mileage.changedTypeAndPoint(removeMileageHistoryType, point);

          deleteMileageList.add(mileage);
          break;
        }
      }
    }

    return deleteMileageList;
  }

  private MileageHistoryType changeRemoveType(MileageHistoryType type) {
    switch (type) {
      case CONTENT_ADD:
        return MileageHistoryType.CONTENT_REMOVE;
      case PHOTO_ADD:
        return MileageHistoryType.PHOTO_REMOVE;
      case REVIEW_ADD:
        return MileageHistoryType.REVIEW_REMOVE;
      default:
        throw new NotMatchMileageTypeException(type.name());
    }
  }
}
