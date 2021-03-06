package com.example.mileageapi.web;

import com.example.mileageapi.service.MileageService;
import com.example.mileageapi.service.dto.MileageHistoryDTO;
import com.example.mileageapi.service.dto.MileageHistoryInfo;
import com.example.mileageapi.service.dto.MileageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MileageController {

  private final MileageService mileageService;

  @GetMapping("my-mileage")
  public ResponseEntity<MileageInfo> getMyMileage(@Valid @NotNull String userId) {
    Optional<MileageInfo> data = mileageService.getMyMileage(UUID.fromString(userId));
    if (data.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    return ResponseEntity.ok().body(data.get());
  }

  @GetMapping("my-mileage-histories")
  public ResponseEntity<Page<MileageHistoryInfo>> getMyMileageHistories(
      @Valid MileageHistoryDTO dto) {
    Page<MileageHistoryInfo> data = mileageService.getMyMileageHistories(dto);
    return ResponseEntity.ok().body(data);
  }
}
