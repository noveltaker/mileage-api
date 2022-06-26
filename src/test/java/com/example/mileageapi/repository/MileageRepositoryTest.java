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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class MileageRepositoryTest {

  @Autowired private MileageRepository mileageRepository;

  @Autowired private MileageHistoryRepository mileageHistoryRepository;

  @Nested
  @DisplayName("조회")
  class Select {

    private Mileage mock;

    private List<MileageHistory> histories;

    private UUID mockUserId = EventDTOMock.userId;

    private UUID mockReviewId = EventDTOMock.reviewId;

    @BeforeEach
    void init() {

      mock = mileageRepository.save(MileageMock.createdMock());

      mileageRepository.flush();

      histories = mileageHistoryRepository.saveAll(MileageHistoryMock.createdMocks(mock));

      mileageHistoryRepository.flush();
    }

    @Test
    @DisplayName("아이디 별로 조회 테스트 케이스")
    void findById() {

      Mileage entity = mileageRepository.findById(mockUserId).orElseThrow();

      Assertions.assertEquals(mock.getUserId(), entity.getUserId());
      Assertions.assertEquals(mock.getPoint(), entity.getPoint());
    }

    @Test
    @DisplayName("유저 별 mileage 와 review 별 히스토리 테스트 케이스")
    void findByUserIdAndHistorySet_ReviewId() {

      Mileage entity =
          mileageRepository
              .findByUserIdAndHistorySet_ReviewId(mockUserId, mockReviewId)
              .orElseThrow();

      Assertions.assertEquals(mock.getUserId(), entity.getUserId());
      Assertions.assertEquals(mock.getPoint(), entity.getPoint());
    }
  }
}
