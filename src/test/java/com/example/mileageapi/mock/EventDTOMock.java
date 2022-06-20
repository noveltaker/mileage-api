package com.example.mileageapi.mock;

import com.example.mileageapi.constants.ActionType;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.List;
import java.util.UUID;

public class EventDTOMock {

    public static final String type = "REVIEW";

    public static final ActionType action = ActionType.ADD;

    public static final UUID reviewId = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");

    public static final String content = "조아요!!";

    public static final List<UUID> attachedPhotoIds = List.of(UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb8"), UUID.fromString("afb0cef2-851d-4a50-bb07-9cc15cbdc332"));

    public static final UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");

    public static final UUID placeId = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");

    public static EventDTO createdMock() {
        return EventDTO.builder().type(type).action(action).reviewId(reviewId).content(content).attachedPhotoIds(attachedPhotoIds).userId(userId).placeId(placeId).build();
    }
}
