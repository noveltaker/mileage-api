package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.config.exception.BaseException;
import com.example.mileageapi.config.exception.NotMatchMileageHistoryTypeException;
import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.constants.MsgType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class DeleteMileagePoint extends AbstractMileagePoint {

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

    EventDTO dto = getDto();

    // review 아이디
    UUID reviewId = dto.getReviewId();

    // 유저의 아이디
    UUID userId = dto.getUserId();

    // 장소 아이디
    UUID placeId = dto.getPlaceId();

    MileageRepository mileageRepository = getMileageRepository();

    Mileage mileage =
        mileageRepository
            .findByUserIdAndHistorySet_ReviewId(userId, reviewId)
            .orElseThrow(() -> new BaseException(MsgType.EmptyMileageData));

    Set<MileageHistory> mileageHistories = mileage.getHistorySet();

    List<MileageHistory> removeHistoryTypeList = new ArrayList<>();

    for (MileageHistoryType type : mileageHistoryTypeList) {

      for (MileageHistory mileageHistory : mileageHistories) {

        if (type.equals(mileageHistory.getType())) {

          removeHistoryTypeList.add(
              MileageHistory.builder()
                  .type(changeRemoveType(type))
                  .point(mileageHistory.getPoint() * -1)
                  .mileage(mileage)
                  .placeId(placeId)
                  .reviewId(reviewId)
                  .build());
          break;
        }
      }
    }

    Integer updatePoint =
        removeHistoryTypeList.stream().map(MileageHistory::getPoint).reduce(0, Integer::sum);

    mileage.sumPoint(updatePoint);

    return removeHistoryTypeList;
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
        throw new NotMatchMileageHistoryTypeException();
    }
  }
}
