package com.example.mileageapi.service.dto;

import com.example.mileageapi.mock.EventDTOMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventDTOTest {

    @Test
    @DisplayName("")
    void createdDTO() {

        EventDTO mock = EventDTOMock.createdMock();

        Assertions.assertEquals(EventDTOMock.type, mock.getType());
        Assertions.assertEquals(EventDTOMock.action, mock.getAction());
        Assertions.assertEquals(EventDTOMock.reviewId, mock.getReviewId());
        Assertions.assertEquals(EventDTOMock.content, mock.getContent());
        Assertions.assertEquals(EventDTOMock.attachedPhotoIds, mock.getAttachedPhotoIds());
        Assertions.assertEquals(EventDTOMock.userId, mock.getUserId());
        Assertions.assertEquals(EventDTOMock.placeId, mock.getPlaceId());
    }
}