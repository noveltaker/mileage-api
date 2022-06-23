package com.example.mileageapi.mock;

import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.domain.Mileage;

import java.util.List;

public class MileageMock {

    public static Mileage createdMock() {
        return Mileage.builder()
                .type(MileageType.REVIEW_ADD)
                .reviewId(EventDTOMock.reviewId)
                .placeId(EventDTOMock.placeId)
                .userId(EventDTOMock.userId)
                .point(1)
                .build();
    }

    public static List<Mileage> createdMocks() {
        return List.of(
                Mileage.builder()
                        .type(MileageType.REVIEW_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build(),
                Mileage.builder()
                        .type(MileageType.CONTENT_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build(),
                Mileage.builder()
                        .type(MileageType.PHOTO_ADD)
                        .reviewId(EventDTOMock.reviewId)
                        .placeId(EventDTOMock.placeId)
                        .userId(EventDTOMock.userId)
                        .point(1)
                        .build()
        );
    }
}
