package com.example.mileageapi.constants;

public enum MsgType {

  // system 관련 코드
  SYSTEM_ERROR("S001", "system exception"),

  // 데이터 관련 코드
  NotMatchMileageHistoryType("D001", "mileage history type is not match"),
  EmptyMileageData("D002", "mileage data is empty"),
  ;

  private final String code;

  private final String message;

  MsgType(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
