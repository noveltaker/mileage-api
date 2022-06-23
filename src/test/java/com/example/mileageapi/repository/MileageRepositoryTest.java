package com.example.mileageapi.repository;

import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.mock.EventDTOMock;
import com.example.mileageapi.mock.MileageMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class MileageRepositoryTest {

    @Autowired
    private MileageRepository mileageRepository;

    @Test
    @DisplayName("마일리지 포인트들 저장 테스트 케이스")
    void save() {

        List<Mileage> mocks = MileageMock.createdMocks();

        List<Mileage> entities = mileageRepository.saveAll(mocks);

        for (Mileage mock : mocks) {

            Mileage entity = entities.stream().filter(foundEntity -> foundEntity.getType().equals(mock.getType())).findFirst().orElseThrow();

            Assertions.assertEquals(mock.getType(), entity.getType());
            Assertions.assertEquals(mock.getReviewId(), entity.getReviewId());
            Assertions.assertEquals(mock.getPlaceId(), entity.getPlaceId());
            Assertions.assertEquals(mock.getUserId(), entity.getUserId());
            Assertions.assertEquals(mock.getPoint(), entity.getPoint());

        }
    }

    @Nested
    @DisplayName("조회")
    class Select {

        private List<Mileage> mocks;

        @BeforeEach
        void init() {
            mocks = mileageRepository.saveAll(MileageMock.createdMocks());
            mileageRepository.flush();
        }

        @Test
        @DisplayName("리뷰 아이디 별로 데이터 조회 테스트 케이스")
        void findByReviewId() {

            List<Mileage> entities = mileageRepository.findByReviewId(EventDTOMock.reviewId);

            for (Mileage mock : mocks) {

                Mileage entity = entities.stream().filter(foundEntity -> foundEntity.getType().equals(mock.getType())).findFirst().orElseThrow();

                Assertions.assertEquals(mock.getType(), entity.getType());
                Assertions.assertEquals(mock.getReviewId(), entity.getReviewId());
                Assertions.assertEquals(mock.getPlaceId(), entity.getPlaceId());
                Assertions.assertEquals(mock.getUserId(), entity.getUserId());
                Assertions.assertEquals(mock.getPoint(), entity.getPoint());

            }
        }
    }
}