package com.example.mileageapi.mock;

import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.service.dto.MileageInfo;

import java.util.UUID;

public class MileageInfoTemp implements MileageInfo {

  private final Mileage mileage;

  public MileageInfoTemp(Mileage mileage) {
    this.mileage = mileage;
  }

  @Override
  public UUID getUserId() {
    return mileage.getUserId();
  }

  @Override
  public Integer getPoint() {
    return mileage.getPoint();
  }
}
