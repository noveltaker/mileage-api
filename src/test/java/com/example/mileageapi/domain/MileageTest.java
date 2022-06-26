package com.example.mileageapi.domain;

import com.example.mileageapi.mock.MileageMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MileageTest {

  @Test
  @DisplayName("sumPoint 메소드 테스트 케이스")
  void sumPoint() {

    Mileage mock = MileageMock.createdMock();

    int mockPoint = -100;

    mock.sumPoint(mockPoint);

    Assertions.assertEquals(mockPoint, mock.getPoint());
  }
}
