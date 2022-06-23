package com.example.mileageapi.repository;

import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.mock.MileageMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

            Mileage entity = entities.stream()
                    .filter(foundEntity -> foundEntity.getType().equals(mock.getType()))
                    .findFirst()
                    .orElseThrow();

            Assertions.assertEquals(mock.getType(), entity.getType());
            Assertions.assertEquals(mock.getReviewId(), entity.getReviewId());
            Assertions.assertEquals(mock.getPlaceId(), entity.getPlaceId());
            Assertions.assertEquals(mock.getUserId(), entity.getUserId());
            Assertions.assertEquals(mock.getPoint(), entity.getPoint());

        }

    }
}