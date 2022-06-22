package com.example.mileageapi.service.dto;

import com.example.mileageapi.constants.ActionType;
import com.example.mileageapi.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    // 이벤트 타입
    private String type;

    // 이벤트에 대한 action 타입
    private ActionType action;

    // 리뷰의 아이디값
    private UUID reviewId;

    // 컼텐츠
    private String content;

    // 사진에 대한 값 리스트
    private List<UUID> attachedPhotoIds;

    // 유저 아이디
    private UUID userId;

    // 장소 아이디
    private UUID placeId;

    public Review toEntity() {
        return Review.builder().reviewId(this.reviewId).content(this.content).attachedPhotoIds(this.attachedPhotoIds).userId(this.userId).placeId(this.placeId).build();
    }
}
