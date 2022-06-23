package com.example.mileageapi.domain;

import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.mock.MileageMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MileageTest {


    @Test
    @DisplayName("changedTypeAndPoint 메소드 테스트 케이스")
    void changedTypeAndPoint() {

        Mileage mock = MileageMock.createdMock();

        MileageType mockType = MileageType.REVIEW_REMOVE;

        int mockPoint = mock.getPoint() * -1;

        mock.changedTypeAndPoint(mockType, mockPoint);

        Assertions.assertEquals(mock.getType(), mockType);
        Assertions.assertEquals(mock.getPoint(), mockPoint);
    }
}