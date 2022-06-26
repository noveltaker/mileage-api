package com.example.mileageapi.web;

import com.example.mileageapi.service.MileageService;
import com.example.mileageapi.service.dto.MyMileage;
import lombok.RequiredArgsConstructor;
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
  public ResponseEntity<MyMileage> getMyMileage(String userId) {
    Optional<MyMileage> data = mileageService.getMyMileage(UUID.fromString(userId));
    if (data.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok().body(data.get());
  }
}
