package com.example.mileageapi.domain;

import com.example.mileageapi.constants.MileageHistoryType;
import com.example.mileageapi.mock.MileageHistoryMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MileageHsitoryTest {


    @Test
    @DisplayName("changedTypeAndPoint 메소드 테스트 케이스")
    void changedTypeAndPoint() {

        MileageHistory mock = MileageHistoryMock.createdMock();

        MileageHistoryType mockType = MileageHistoryType.REVIEW_REMOVE;

        int mockPoint = mock.getPoint() * -1;

        mock.changedTypeAndPoint(mockType, mockPoint);

        Assertions.assertEquals(mock.getType(), mockType);
        Assertions.assertEquals(mock.getPoint(), mockPoint);
    }
}