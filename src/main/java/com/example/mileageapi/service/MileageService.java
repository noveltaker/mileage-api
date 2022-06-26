package com.example.mileageapi.service;

import com.example.mileageapi.service.dto.MileageInfo;

import java.util.Optional;
import java.util.UUID;

public interface MileageService {

  Optional<MileageInfo> getMyMileage(UUID userId);
}
