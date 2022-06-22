package com.example.mileageapi.config.aspect.mileage;

import com.example.mileageapi.constants.ActionType;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

public class MileagePoint implements Point {

    private final MileageRepository mileageRepository;

    private final EventDTO dto;

    public MileagePoint(MileageRepository mileageRepository, EventDTO dto) {
        this.mileageRepository = mileageRepository;
        this.dto = dto;
    }

    @Override
    public void getPoint() {

        ActionType action = dto.getAction();

        Point point = null;

        switch (action) {
            case ADD:
                point = new AddMileagePoint(dto, mileageRepository);
                break;
            case DELETE:
                point = new DeleteMileagePoint(dto, mileageRepository);
                break;
        }

        point.getPoint();
    }
}
