package com.example.mileageapi.config.aspect.mileage;

import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeleteMileagePoint extends AbstractMileagePoint {

    private final List<MileageType> mileageTypeList = List.of(
            MileageType.CONTENT_ADD,
            MileageType.PHOTO_ADD,
            MileageType.REVIEW_ADD
    );

    public DeleteMileagePoint(EventDTO dto, MileageRepository mileageRepository) {
        super(dto, mileageRepository);
    }

    @Override
    public List<Mileage> getPoints() {

        EventDTO dto = this.getDto();

        MileageRepository mileageRepository = this.getMileageRepository();

        UUID reviewId = dto.getReviewId();

        List<Mileage> reviewMileageList = mileageRepository.findByReviewId(reviewId);

        List<Mileage> deleteMileageList = new ArrayList<>();

        for (MileageType type : mileageTypeList) {
            for (Mileage mileage : reviewMileageList) {
                if (type.equals(mileage.getType())) {
                    mileage.changedTypeAndCount(changeRemoveType(mileage.getType()), mileage.getCount() * -1);
                    deleteMileageList.add(mileage);
                    break;
                }
            }
        }

       return deleteMileageList;
    }

    private MileageType changeRemoveType(MileageType type) {
        switch (type) {
            case CONTENT_ADD:
                return MileageType.CONTENT_REMOVE;
            case PHOTO_ADD:
                return MileageType.PHOTO_REMOVE;
            case REVIEW_ADD:
                return MileageType.REVIEW_REMOVE;
            default:
                // todo : exception
                throw new NullPointerException();
        }
    }
}
