package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.config.exception.BaseException;
import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.constants.MsgType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.*;
import java.util.stream.Collectors;

public final class ModMileagePoint extends AbstractMileagePoint {

  public ModMileagePoint(
      EventDTO dto,
      MileageHistoryRepository mileageHistoryRepository,
      MileageRepository mileageRepository) {
    super(dto, mileageRepository, mileageHistoryRepository);
  }

  @Override
  public List<MileageHistory> getPoints() {

    EventDTO dto = getDto();

    // 리뷰 id
    UUID reviewId = dto.getReviewId();

    // 유저 id
    UUID userId = dto.getUserId();

    MileageRepository mileageRepository = getMileageRepository();

    Mileage mileage =
        mileageRepository
            .findByUserIdAndHistorySet_ReviewId(userId, reviewId)
            .orElseThrow(() -> new BaseException(MsgType.EmptyMileageData));

    List<MileageHistory> newHistories = new ArrayList<>();

    setContentHistory(dto, mileage, newHistories);

    setPhotoHistory(dto, mileage, newHistories);

    Integer updatePoint =
        newHistories.stream().map(MileageHistory::getPoint).reduce(0, Integer::sum);

    mileage.sumPoint(updatePoint);

    return newHistories;
  }

  private void setContentHistory(EventDTO dto, Mileage mileage, List<MileageHistory> newHistories) {

    // 히스토리 리스트
    Set<MileageHistory> histories = mileage.getHistorySet();

    // 리뷰 id
    UUID reviewId = dto.getReviewId();

    // 장소 기준으로 첫 리뷰 작성
    UUID placeId = dto.getPlaceId();

    // 컨텐츠 데이터
    String content = dto.getContent();

    int contentLength = content.length();

    List<MileageHistory> contentHistories =
        histories.stream()
            .filter(
                history ->
                    MileageHistoryType.CONTENT_ADD.equals(history.getType())
                        || MileageHistoryType.CONTENT_MOD.equals(history.getType()))
            .sorted(Comparator.comparing(MileageHistory::getCreatedDate).reversed())
            .collect(Collectors.toList());

    int contentHistorySize = contentHistories.size();

    // content 관련
    if (contentLength > 0) {

      // 마일리지가 없다면 추가 mod 타입 추가
      // 크기 값이 1 이면서 history 의 최신 값이 add 타입이라면 추가
      if (contentHistorySize == 0
          || (contentHistorySize == 1
              && MileageHistoryType.CONTENT_ADD.equals(contentHistories.get(0).getType()))) {
        newHistories.add(
            MileageHistory.builder()
                .type(MileageHistoryType.CONTENT_MOD)
                .point(1)
                .placeId(placeId)
                .mileage(mileage)
                .reviewId(reviewId)
                .build());
      }

    } else {
      // 회수 로직

      if (contentHistorySize > 0) {

        MileageHistory currentMileageHistory = contentHistories.get(0);

        newHistories.add(
            MileageHistory.builder()
                .type(MileageHistoryType.CONTENT_REMOVE)
                .point(currentMileageHistory.getPoint() * -1)
                .placeId(placeId)
                .mileage(mileage)
                .reviewId(reviewId)
                .build());
      }
    }
  }

  private void setPhotoHistory(EventDTO dto, Mileage mileage, List<MileageHistory> newHistories) {

    // 컨텐츠 데이터
    List<UUID> attachedPhotoIds = dto.getAttachedPhotoIds();

    // 히스토리 리스트
    Set<MileageHistory> histories = mileage.getHistorySet();

    // 리뷰 id
    UUID reviewId = dto.getReviewId();

    // 장소 기준으로 첫 리뷰 작성
    UUID placeId = dto.getPlaceId();

    int attachedPhotoIdSize = attachedPhotoIds.size();

    List<MileageHistory> photoHistories =
        histories.stream()
            .filter(
                history ->
                    MileageHistoryType.PHOTO_ADD.equals(history.getType())
                        || MileageHistoryType.PHOTO_ADD.equals(history.getType()))
            .sorted(Comparator.comparing(MileageHistory::getCreatedDate).reversed())
            .collect(Collectors.toList());

    int photoHistorySize = photoHistories.size();

    if (attachedPhotoIdSize > 0) {

      // 마일리지가 없다면 추가 mod 타입 추가
      // 크기 값이 1 이면서 history 의 최신 값이 add 타입이라면 추가
      if (photoHistorySize == 0
          || (photoHistorySize == 1
              && MileageHistoryType.CONTENT_ADD.equals(photoHistories.get(0).getType()))) {
        newHistories.add(
            MileageHistory.builder()
                .type(MileageHistoryType.PHOTO_MOD)
                .point(1)
                .placeId(placeId)
                .mileage(mileage)
                .reviewId(reviewId)
                .build());
      }

    } else {
      // 회수 로직

      if (photoHistorySize > 0) {

        MileageHistory currentMileageHistory = photoHistories.get(0);

        newHistories.add(
            MileageHistory.builder()
                .type(MileageHistoryType.PHOTO_REMOVE)
                .point(currentMileageHistory.getPoint() * -1)
                .placeId(placeId)
                .mileage(mileage)
                .reviewId(reviewId)
                .build());
      }
    }
  }
}
