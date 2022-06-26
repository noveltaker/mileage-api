package com.example.mileageapi.config.exception;

import com.example.mileageapi.constants.MsgType;

public class BaseException extends RuntimeException {

  private final MsgType msgType;

  public BaseException(MsgType msgType) {
    super(msgType.name());
    this.msgType = msgType;
  }

  public MsgType getMsgType() {
    return msgType;
  }
}
