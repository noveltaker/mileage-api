package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.config.exception.NotMatchMileageTypeException;
import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeleteMileagePoint extends AbstractMileagePoint {

  private final List<MileageType> mileageTypeList =
      List.of(MileageType.CONTENT_ADD, MileageType.PHOTO_ADD, MileageType.REVIEW_ADD);

  public DeleteMileagePoint(EventDTO dto, MileageHistoryRepository mileageHistoryRepository) {
    super(dto, mileageHistoryRepository);
  }

  @Override
  public List<MileageHistory> getPoints() {

    EventDTO dto = this.getDto();

    MileageHistoryRepository mileageHistoryRepository = this.getMileageRepository();

    UUID reviewId = dto.getReviewId();

    List<MileageHistory> reviewMileageList = mileageHistoryRepository.findByReviewId(reviewId);

    List<MileageHistory> deleteMileageList = new ArrayList<>();

    for (MileageType type : mileageTypeList) {

      for (MileageHistory mileage : reviewMileageList) {
        if (type.equals(mileage.getType())) {

          MileageType removeMileageType = changeRemoveType(mileage.getType());

          int point = mileage.getPoint() * -1;

          mileage.changedTypeAndPoint(removeMileageType, point);

          deleteMileageList.add(mileage);
          break;
        }
      }
    }

    return deleteMileageList;
  }

  private MileageType changeRemoveType(MileageType type) {
    switch (type) {
      case CONTENT_ADD:
        return MileageType.CONTENT_REMOVE;
      case PHOTO_ADD:
        return MileageType.PHOTO_REMOVE;
      case REVIEW_ADD:
        return MileageType.REVIEW_REMOVE;
      default:
        throw new NotMatchMileageTypeException(type.name());
    }
  }
}
