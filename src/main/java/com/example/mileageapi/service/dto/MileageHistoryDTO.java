package com.example.mileageapi.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageHistoryDTO {

  @NotNull private Integer size;

  @NotNull private Integer page;

  @NotNull private UUID userId;

  public PageRequest getPageRequest() {
    return PageRequest.of(this.size, this.page);
  }
}
