package com.example.mileageapi.config.aspect.mileage;

import com.example.mileageapi.constants.ActionType;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

public class MileagePoint {

    private final MileageRepository mileageRepository;

    private final EventDTO dto;

    private Point point;

    public MileagePoint(MileageRepository mileageRepository, EventDTO dto) {
        this.mileageRepository = mileageRepository;
        this.dto = dto;
    }

    public void configure() {

        ActionType action = dto.getAction();

        switch (action) {
            case ADD:
                point = new AddMileagePoint(dto, mileageRepository);
                break;
            case DELETE:
                point = new DeleteMileagePoint(dto, mileageRepository);
                break;
        }

    }

    public void getPoint() {
        point.getPoint();
    }
}
