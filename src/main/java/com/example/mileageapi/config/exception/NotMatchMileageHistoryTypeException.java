package com.example.mileageapi.config.exception;

import com.example.mileageapi.constants.MsgType;

public final class NotMatchMileageHistoryTypeException extends BaseException {
  public NotMatchMileageHistoryTypeException() {
    super(MsgType.NotMatchMileageHistoryType);
  }
}
