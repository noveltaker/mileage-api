package com.example.mileageapi.config.aspect.mileagepoint;

import com.example.mileageapi.constants.ActionType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;

import java.util.List;

public class MileagePoint {

    private final MileageRepository mileageRepository;

    private final EventDTO dto;

    private Point point;

    public MileagePoint(MileageRepository mileageRepository, EventDTO dto) {
        this.mileageRepository = mileageRepository;
        this.dto = dto;
    }

    public void createMileage() {

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

    public void saveMileagePoints() {
        List<Mileage> points = point.getPoints();
        mileageRepository.saveAll(points);
    }
}
