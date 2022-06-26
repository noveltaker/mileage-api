package com.example.mileageapi.service;

import com.example.mileageapi.service.dto.MyMileage;

import java.util.Optional;
import java.util.UUID;

public interface MileageService {

  Optional<MyMileage> getMyMileage(UUID userId);
}
