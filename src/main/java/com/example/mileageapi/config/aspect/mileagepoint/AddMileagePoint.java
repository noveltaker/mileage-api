package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AddMileagePoint extends AbstractMileagePoint {

  public AddMileagePoint(EventDTO dto, MileageHistoryRepository mileageHistoryRepository) {
    super(dto, mileageHistoryRepository);
  }

  @Override
  public List<MileageHistory> getPoints() {

    MileageHistoryRepository mileageHistoryRepository = getMileageRepository();

    EventDTO dto = this.getDto();

    List<MileageHistory> mileageList = new ArrayList<>();

    // 리뷰 id
    UUID reviewId = dto.getReviewId();

    // 유저 id
    UUID userId = dto.getUserId();

    // 장소 기준으로 첫 리뷰 작성
    UUID placeId = dto.getPlaceId();

    long reviewCount = mileageHistoryRepository.countByPlaceId(placeId);

    if (reviewCount == 0) {
      mileageList.add(
          MileageHistory.builder()
              .type(MileageType.REVIEW_ADD)
              .reviewId(reviewId)
              .userId(userId)
              .placeId(placeId)
              .point(1)
              .build());
    }

    String content = dto.getContent();

    int contentLength = content.length();

    // 컨텐츠 길이가 1 이상일떄 포인트 추가
    if (contentLength > 0) {
      mileageList.add(
          MileageHistory.builder()
              .type(MileageType.CONTENT_ADD)
              .reviewId(reviewId)
              .userId(userId)
              .placeId(placeId)
              .point(1)
              .build());
    }

    List<UUID> attachedPhotoIds = dto.getAttachedPhotoIds();

    // 사진의 갯수가 1 이상일때 포인트 추가
    if (attachedPhotoIds.size() > 0) {
      mileageList.add(
          MileageHistory.builder()
              .type(MileageType.PHOTO_ADD)
              .reviewId(reviewId)
              .userId(userId)
              .placeId(placeId)
              .point(1)
              .build());
    }

    return mileageList;
  }
}
