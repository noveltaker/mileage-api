package com.example.mileageapi.mock;

import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.domain.MileageHistory;

import java.util.List;

public class MileageHistoryMock {

    public static MileageHistory createdMock() {
        return MileageHistory.builder()
                .type(MileageType.REVIEW_ADD)
                .reviewId(EventDTOMock.reviewId)
                .placeId(EventDTOMock.placeId)
                .userId(EventDTOMock.userId)
                .point(1)
                .build();
    }

    public static List<MileageHistory> createdMocks() {
        return List.of(
                MileageHistory.builder()
                        .type(MileageType.REVIEW_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build(),
                MileageHistory.builder()
                        .type(MileageType.CONTENT_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build(),
                MileageHistory.builder()
                        .type(MileageType.PHOTO_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build()
        );
    }
}
