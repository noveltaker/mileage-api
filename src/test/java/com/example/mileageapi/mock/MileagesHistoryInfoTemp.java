package com.example.mileageapi.mock;

import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.service.dto.MileageHistoryInfo;

import java.util.UUID;

public class MileagesHistoryInfoTemp implements MileageHistoryInfo {

  private final MileageHistory mileageHistory;

  public MileagesHistoryInfoTemp(MileageHistory mileageHistory) {
    this.mileageHistory = mileageHistory;
  }

  @Override
  public Long getId() {
    return mileageHistory.getId();
  }

  @Override
  public UUID getReviewId() {
    return mileageHistory.getReviewId();
  }

  @Override
  public MileageHistoryType getType() {
    return mileageHistory.getType();
  }

  @Override
  public UUID getPlaceId() {
    return mileageHistory.getPlaceId();
  }

  @Override
  public Integer getPoint() {
    return mileageHistory.getPoint();
  }

  @Override
  public UUID getUserId() {
    return mileageHistory.getMileage().getUserId();
  }
}
