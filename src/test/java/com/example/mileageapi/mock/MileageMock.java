package com.example.mileageapi.mock;

import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.service.dto.MileageInfo;

public class MileageMock {

  public static Mileage createdMock() {
    return Mileage.builder().userId(EventDTOMock.userId).point(0).build();
  }

  public static MileageInfo createdMileageInfoMock() {
    return new MileageInfoTemp(createdMock());
  }
}
