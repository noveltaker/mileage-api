package com.example.mileageapi.mock;

import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.domain.MileageHistory;

import java.util.List;

public class MileageHistoryMock {

  public static MileageHistory createdMock(Mileage mileage) {
    return MileageHistory.builder()
        .type(MileageHistoryType.REVIEW_ADD)
        .reviewId(EventDTOMock.reviewId)
        .placeId(EventDTOMock.placeId)
        .mileage(mileage)
        .point(1)
        .build();
  }

  public static List<MileageHistory> createdMocks(Mileage mileage) {
    return List.of(
        MileageHistory.builder()
            .type(MileageHistoryType.REVIEW_ADD)
            .reviewId(EventDTOMock.reviewId)
            .placeId(EventDTOMock.placeId)
            .point(1)
            .mileage(mileage)
            .build(),
        MileageHistory.builder()
            .type(MileageHistoryType.CONTENT_ADD)
            .reviewId(EventDTOMock.reviewId)
            .placeId(EventDTOMock.placeId)
            .point(1)
            .mileage(mileage)
            .build(),
        MileageHistory.builder()
            .type(MileageHistoryType.PHOTO_ADD)
            .reviewId(EventDTOMock.reviewId)
            .placeId(EventDTOMock.placeId)
            .point(1)
            .mileage(mileage)
            .build());
  }
}
