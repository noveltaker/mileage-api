package com.example.mileageapi.service;

import com.example.mileageapi.service.dto.MileageHistoryInfo;
import com.example.mileageapi.service.dto.MileageInfo;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface MileageService {

  Optional<MileageInfo> getMyMileage(UUID userId);

  Page<MileageHistoryInfo> getMyMileageHistories(UUID userId);
}
