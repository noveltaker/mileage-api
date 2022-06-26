package com.example.mileageapi.repository;

import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.domain.MileageHistory;
import com.example.mileageapi.mock.EventDTOMock;
import com.example.mileageapi.mock.MileageHistoryMock;
import com.example.mileageapi.mock.MileageMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class MileageHistoryRepositoryTest {

  @Autowired private MileageHistoryRepository mileageHistoryRepository;

  @Autowired private MileageRepository mileageRepository;

  private Mileage mileage;

  @BeforeEach
  void init() {
    mileage = mileageRepository.save(MileageMock.createdMock());
    mileageRepository.flush();
  }

  @Test
  @DisplayName("마일리지 포인트들 저장 테스트 케이스")
  void save() {

    List<MileageHistory> mocks = MileageHistoryMock.createdMocks(mileage);

    List<MileageHistory> entities = mileageHistoryRepository.saveAll(mocks);

    for (MileageHistory mock : mocks) {

      MileageHistory entity =
          entities.stream()
              .filter(foundEntity -> foundEntity.getType().equals(mock.getType()))
              .findFirst()
              .orElseThrow();

      Assertions.assertEquals(mock.getType(), entity.getType());
      Assertions.assertEquals(mock.getReviewId(), entity.getReviewId());
      Assertions.assertEquals(mock.getPlaceId(), entity.getPlaceId());
      Assertions.assertEquals(mock.getPoint(), entity.getPoint());
    }
  }

  @Nested
  @DisplayName("조회")
  class Select {

    private List<MileageHistory> mocks;

    @BeforeEach
    void init() {
      mocks = mileageHistoryRepository.saveAll(MileageHistoryMock.createdMocks(mileage));
      mileageHistoryRepository.flush();
    }

    @Test
    @DisplayName("리뷰 아이디 별로 데이터 조회 테스트 케이스")
    void findByReviewId() {

      List<MileageHistory> entities =
          mileageHistoryRepository.findByReviewId(EventDTOMock.reviewId);

      for (MileageHistory mock : mocks) {

        MileageHistory entity =
            entities.stream()
                .filter(foundEntity -> foundEntity.getType().equals(mock.getType()))
                .findFirst()
                .orElseThrow();

        Assertions.assertEquals(mock.getType(), entity.getType());
        Assertions.assertEquals(mock.getReviewId(), entity.getReviewId());
        Assertions.assertEquals(mock.getPlaceId(), entity.getPlaceId());
        Assertions.assertEquals(mock.getPoint(), entity.getPoint());
      }
    }
  }
}
