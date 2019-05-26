package com.dongweima.component.exception;

public class BusinessException extends RuntimeException implements LocalError {

  private int code = 10000;

  public BusinessException() {
  }

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessException(Throwable cause) {
    super(cause);
  }

  public BusinessException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public BusinessException(CommonError nbugsCommonError) {
    super(nbugsCommonError.getMsg());
    this.code = nbugsCommonError.getCode();
  }

  public BusinessException(CommonError nbugsCommonError, Throwable cause) {
    super(nbugsCommonError.getMsg(), cause);
    this.code = nbugsCommonError.getCode();
  }

  public Integer getCode() {
    return code;
  }

}
