package com.example.mileageapi.service.dto;

import com.example.mileageapi.constants.MileageHistoryType;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface MileageHistoryInfo {

  Long getId();

  UUID getReviewId();

  MileageHistoryType getType();

  UUID getPlaceId();

  Integer getPoint();

  @Value("#{target.mileage.userId}")
  UUID getUserId();
}
