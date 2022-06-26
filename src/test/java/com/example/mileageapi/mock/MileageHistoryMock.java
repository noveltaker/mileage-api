package com.example.mileageapi.mock;

import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.domain.MileageHistory;

import java.util.List;

public class MileageHistoryMock {

    public static MileageHistory createdMock() {
        return MileageHistory.builder()
                .type(MileageHistoryType.REVIEW_ADD)
                .reviewId(EventDTOMock.reviewId)
                .placeId(EventDTOMock.placeId)
//                .userId(EventDTOMock.userId)
                .point(1)
                .build();
    }

    public static List<MileageHistory> createdMocks() {
        return List.of(
                MileageHistory.builder()
                        .type(MileageHistoryType.REVIEW_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
//                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build(),
                MileageHistory.builder()
                        .type(MileageHistoryType.CONTENT_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
//                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build(),
                MileageHistory.builder()
                        .type(MileageHistoryType.PHOTO_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
//                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build()
        );
    }
}
