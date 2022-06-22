package com.example.mileageapi.config.aspect;

import com.example.mileageapi.config.aspect.mileage.MileagePoint;
import com.example.mileageapi.constants.ActionType;
import com.example.mileageapi.constants.MileageType;
import com.example.mileageapi.domain.Mileage;
import com.example.mileageapi.repository.MileageRepository;
import com.example.mileageapi.service.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
@RequiredArgsConstructor
public class MileageAspect {

    private final MileageRepository mileageRepository;

    @Pointcut("execution(* com.example.mileageapi.service.EventService.createdReview(..))")
    public void onRequest() {
    }

    @Around("com.example.mileageapi.config.aspect.MileageAspect.onRequest()")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {

        Map requestBody = getParams(pjp);

        EventDTO dto = (EventDTO) requestBody.get("dto");

        MileagePoint mileagePoint = new MileagePoint(mileageRepository, dto);

        mileagePoint.getPoint();

        return pjp.proceed(pjp.getArgs());
    }

    private Map getParams(JoinPoint joinPoint) {
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            params.put(parameterNames[i], args[i]);
        }
        return params;
    }
}
