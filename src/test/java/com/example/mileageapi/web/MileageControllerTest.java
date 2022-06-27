package com.example.mileageapi.web;

import com.example.mileageapi.mock.EventDTOMock;
import com.example.mileageapi.mock.MileageMock;
import com.example.mileageapi.service.MileageService;
import com.example.mileageapi.service.dto.MileageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("h2")
@ExtendWith(SpringExtension.class)
@WebMvcTest(MileageController.class)
class MileageControllerTest {

  private MockMvc mockMvc;

  @MockBean private MileageService mileageService;

  @BeforeEach
  void init() {
    this.mockMvc =
        MockMvcBuilders.standaloneSetup(new MileageController(mileageService))
            .addFilter(new CharacterEncodingFilter("UTF-8", true))
            .build();
  }

  @Test
  @DisplayName("나의 마일리지 포인트 API 테스트 케이스")
  void getMyMileage_success() throws Exception {

    Optional<MileageInfo> mockOptional = Optional.of(MileageMock.createdMileageInfoMock());

    BDDMockito.given(mileageService.getMyMileage(any())).willReturn(mockOptional);

    ResultActions action =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/my-mileage")
                    .param("userId", EventDTOMock.userId.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
            .andDo(print());

    BDDMockito.then(mileageService).should().getMyMileage(any());

    String mockUserId = mockOptional.map(MileageInfo::getUserId).orElseThrow().toString();
    String point = mockOptional.map(MileageInfo::getPoint).orElseThrow().toString();

    action
        .andExpect(status().isOk())
        .andExpect(jsonPath("$['userId']").value(mockUserId))
        .andExpect(jsonPath("$['point']").value(point));
  }
}
