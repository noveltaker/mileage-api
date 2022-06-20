package com.example.mileageapi.service.event;

import com.example.mileageapi.service.dto.EventDTO;
import org.springframework.context.ApplicationEvent;

public class MileageEvent extends ApplicationEvent {

    private final EventDTO dto;

    public MileageEvent(Object source, EventDTO dto) {
        super(source);
        this.dto = dto;
    }


    public EventDTO getEventDto() {
        return this.dto;
    }
}
