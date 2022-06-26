package com.example.mileageapi.web;

import com.example.mileageapi.service.MileageService;
import com.example.mileageapi.service.dto.MileageHistoryInfo;
import com.example.mileageapi.service.dto.MileageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MileageController {

  private final MileageService mileageService;

  @GetMapping("my=mileage")
  public ResponseEntity<MileageInfo> getMyMileage(String userId) {
    Optional<MileageInfo> data = mileageService.getMyMileage(UUID.fromString(userId));
    if (data.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok().body(data.get());
  }

  @GetMapping("my-milesage-histories")
  public ResponseEntity<Page<MileageHistoryInfo>> getMyMileageHistories(UUID userId) {
    Page<MileageHistoryInfo> data = mileageService.getMyMileageHistories(userId);
    return ResponseEntity.ok().body(data);
  }
}
