package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class AddMileagePoint extends AbstractMileagePoint {

  public AddMileagePoint(
      EventDTO dto,
      MileageHistoryRepository mileageHistoryRepository,
      MileageRepository mileageRepository) {
    super(dto, mileageRepository, mileageHistoryRepository);
  }

  @Override
  public List<MileageHistory> getPoints() {

    MileageHistoryRepository mileageHistoryRepository = getMileageHistoryRepository();

    EventDTO dto = this.getDto();

    // 리뷰 id
    UUID reviewId = dto.getReviewId();

    // 유저 id
    UUID userId = dto.getUserId();

    // 장소 기준으로 첫 리뷰 작성
    UUID placeId = dto.getPlaceId();

    // 나의 마일리지 포인트 조회
    Mileage mileage = getMyMileage(userId);

    List<MileageHistory> mileageList = new ArrayList<>();

    long reviewCount = mileageHistoryRepository.countByPlaceId(placeId);

    if (reviewCount == 0) {
      mileageList.add(
          MileageHistory.builder()
              .type(MileageHistoryType.REVIEW_ADD)
              .reviewId(reviewId)
              .mileage(mileage)
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
              .type(MileageHistoryType.CONTENT_ADD)
              .reviewId(reviewId)
              .mileage(mileage)
              .placeId(placeId)
              .point(1)
              .build());
    }

    List<UUID> attachedPhotoIds = dto.getAttachedPhotoIds();

    // 사진의 갯수가 1 이상일때 포인트 추가
    if (attachedPhotoIds.size() > 0) {
      mileageList.add(
          MileageHistory.builder()
              .type(MileageHistoryType.PHOTO_ADD)
              .reviewId(reviewId)
              .mileage(mileage)
              .placeId(placeId)
              .point(1)
              .build());
    }

    Integer updatePoint =
        mileageList.stream().map(MileageHistory::getPoint).reduce(0, Integer::sum);

    mileage.sumPoint(updatePoint);

    return mileageList;
  }

  private Mileage getMyMileage(UUID userId) {

    MileageRepository mileageRepository = getMileageRepository();

    Optional<Mileage> mileageOptional = mileageRepository.findById(userId);

    if (mileageOptional.isPresent()) {
      return mileageOptional.get();
    }

    return mileageRepository.save(Mileage.builder().userId(userId).point(0).build());
  }
}
