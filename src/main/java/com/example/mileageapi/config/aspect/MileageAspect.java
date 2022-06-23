package com.example.mileageapi.config.aspect;

import com.example.mileageapi.repository.MileageHistoryRepository;
import com.example.mileageapi.service.dto.EventDTO;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class MileageAspect {

  private final String REVIEW_TYPE = "REVIEW";

  private final MileageHistoryRepository mileageHistoryRepository;

  @Pointcut("execution(* com.example.mileageapi.service.EventService.createdReview(..))")
  public void onRequest() {}

  @Around("com.example.mileageapi.config.aspect.MileageAspect.onRequest()")
  public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {

    Map requestBody = getParams(pjp);

    EventDTO dto = (EventDTO) requestBody.get("dto");

    // 타입값이 리뷰일때
    if (REVIEW_TYPE.equals(dto.getType())) {
      dto.getAction().saveMileagePoints(dto, mileageHistoryRepository);
    }

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
