package com.example.mileageapi.config.aspect.mileage;

import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class AddMileagePoint extends AbstractMileagePoint {

    public AddMileagePoint(EventDTO dto, MileageRepository mileageRepository) {
        super(dto, mileageRepository);
    }

    @Override
    public List<Mileage> getPoints() {

        MileageRepository mileageRepository = getMileageRepository();

        EventDTO dto = this.getDto();

        List<Mileage> mileageList = new ArrayList<>();

        //첫번쨰 리뷰 작성
        UUID reviewId = dto.getReviewId();

        long reviewCount = mileageRepository.countByReviewId(reviewId);

        if (reviewCount > 0) {
            mileageList.add(Mileage.builder().type(MileageType.REVIEW_ADD).reviewId(dto.getReviewId()).count(1).build());
        }

        String content = dto.getContent();

        int contentLength = content.length();

        // 컨텐츠 길이가 1 이상일떄 포인트 추가
        if (contentLength > 0) {
            mileageList.add(Mileage.builder().type(MileageType.CONTENT_ADD).reviewId(dto.getReviewId()).count(1).build());
        }

        List<UUID> attachedPhotoIds = dto.getAttachedPhotoIds();

        // 사진의 갯수가 1 이상일때 포인트 추가
        if (attachedPhotoIds.size() > 0) {
            mileageList.add(Mileage.builder().type(MileageType.PHOTO_ADD).reviewId(dto.getReviewId()).count(1).build());
        }

        return mileageList;
    }
}
