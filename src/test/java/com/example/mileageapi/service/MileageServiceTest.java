package com.example.mileageapi.service;

import com.example.mileageapi.mock.EventDTOMock;
import com.example.mileageapi.mock.MileageMock;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.MileageInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class MileageServiceTest {

  private MileageService mileageService;

  @Mock private MileageRepository mileageRepository;

  @Mock private MileageHistoryRepository mileageHistoryRepository;

  private UUID mockUserId = EventDTOMock.userId;

  @BeforeEach
  void init() {

    mileageService = new MileageServiceImpl(mileageRepository, mileageHistoryRepository);
  }

  @Test
  @DisplayName("나의 마일리지 포인트 조회 테스트 케이스")
  void getMyMileage() {

    Optional<MileageInfo> mockOptional = Optional.of(MileageMock.createdMileageInfoMock());

    BDDMockito.given(mileageRepository.findByUserId(any(), eq(MileageInfo.class)))
        .willReturn(mockOptional);

    Optional<MileageInfo> entityOptional = mileageService.getMyMileage(mockUserId);

    MileageInfo entity = entityOptional.get();

    MileageInfo mock = mockOptional.get();

    Assertions.assertEquals(entity.getUserId(), mock.getUserId());
    Assertions.assertEquals(entity.getPoint(), mock.getPoint());
  }
}
