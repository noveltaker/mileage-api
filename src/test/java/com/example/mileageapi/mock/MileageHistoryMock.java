package com.example.mileageapi.mock;

import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.service.dto.MileageHistoryDTO;
import com.example.mileageapi.service.dto.MileageHistoryInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

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
            .id(1L)
            .type(MileageHistoryType.REVIEW_ADD)
            .reviewId(EventDTOMock.reviewId)
            .placeId(EventDTOMock.placeId)
            .point(1)
            .mileage(mileage)
            .build(),
        MileageHistory.builder()
            .id(2L)
            .type(MileageHistoryType.CONTENT_ADD)
            .reviewId(EventDTOMock.reviewId)
            .placeId(EventDTOMock.placeId)
            .point(1)
            .mileage(mileage)
            .build(),
        MileageHistory.builder()
            .id(3L)
            .type(MileageHistoryType.PHOTO_ADD)
            .reviewId(EventDTOMock.reviewId)
            .placeId(EventDTOMock.placeId)
            .point(1)
            .mileage(mileage)
            .build());
  }

  public static PageRequest getPageable() {
    return PageRequest.of(0, 10);
  }

  public static Page<MileageHistoryInfo> getPageMileageHistoryInfoList(Mileage mileage) {

    List<MileageHistoryInfo> list =
        createdMocks(mileage).stream()
            .map(MileagesHistoryInfoTemp::new)
            .map(map -> (MileageHistoryInfo) map)
            .collect(Collectors.toList());

    PageRequest pageable = getPageable();

    return new PageImpl<>(list, pageable, list.size());
  }

  public static MileageHistoryDTO getHistoryDTO() {
    return new MileageHistoryDTO(0, 10, EventDTOMock.userId);
  }
}
