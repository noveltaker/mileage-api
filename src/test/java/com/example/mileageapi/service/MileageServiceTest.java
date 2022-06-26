package com.example.mileageapi.service;

import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.mock.EventDTOMock;
import com.example.mileageapi.mock.MileageHistoryMock;
import com.example.mileageapi.mock.MileageMock;
import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.MileageHistoryDTO;
import com.example.mileageapi.service.dto.MileageHistoryInfo;
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
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    BDDMockito.then(mileageRepository).should().findByUserId(any(), eq(MileageInfo.class));

    Assertions.assertEquals(entity.getUserId(), mock.getUserId());
    Assertions.assertEquals(entity.getPoint(), mock.getPoint());
  }

  @Test
  @DisplayName("유저별 마일리지 히스토리 리스트 조회")
  void getMyMileageHistories() {

    Page<MileageHistoryInfo> pageMocks =
        MileageHistoryMock.getPageMileageHistoryInfoList(MileageMock.createdMock());

    MileageHistoryDTO dto = MileageHistoryMock.getHistoryDTO();

    BDDMockito.given(
            mileageHistoryRepository.findByMileage_UserId(
                any(), any(), eq(MileageHistoryInfo.class)))
        .willReturn(pageMocks);

    Page<MileageHistoryInfo> pageEntities = mileageService.getMyMileageHistories(dto);

    BDDMockito.then(mileageHistoryRepository)
        .should()
        .findByMileage_UserId(any(), any(), eq(MileageHistoryInfo.class));

    List<MileageHistoryInfo> entities = pageEntities.getContent();

    List<MileageHistoryInfo> mocks = pageMocks.getContent();

    for (MileageHistoryInfo mock : mocks) {

      MileageHistoryInfo entity =
          entities.stream()
              .filter(value -> value.getId().equals(mock.getId()))
              .findFirst()
              .orElseThrow();

      Assertions.assertEquals(mock.getType(), entity.getType());
      Assertions.assertEquals(mock.getReviewId(), entity.getReviewId());
      Assertions.assertEquals(mock.getPlaceId(), entity.getPlaceId());
      Assertions.assertEquals(mock.getPoint(), entity.getPoint());
    }
  }
}
