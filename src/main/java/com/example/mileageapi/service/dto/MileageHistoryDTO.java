package com.example.mileageapi.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class MileageHistoryDTO {

  private Integer size;

  private Integer page;

  private UUID userId;

  public PageRequest getPageRequest() {
    return PageRequest.of(this.size, this.page);
  }
}
