package com.example.mileageapi.service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MileageEventListener implements ApplicationListener<MileageEvent> {

    @Override
    public void onApplicationEvent(MileageEvent event) {
        log.info("success");
    }
}
