package com.example.mileageapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageHistoryDTO {

  private Integer size;

  private Integer page;

  private UUID userId;

  public PageRequest getPageRequest() {
    return PageRequest.of(this.size, this.page);
  }
}
