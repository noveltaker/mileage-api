package com.example.mileageapi.mock;

import com.example.mileageapi.domain.Mileage;

public class MileageMock {

  public static Mileage createdMock() {
    return Mileage.builder().userId(EventDTOMock.userId).point(0).build();
  }
}
