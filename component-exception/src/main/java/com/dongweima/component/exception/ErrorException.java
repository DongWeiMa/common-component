package com.dongweima.component.exception;

public class ErrorException extends RuntimeException implements LocalError {

  private int code = 21111;

  public ErrorException() {
  }

  public ErrorException(String message) {
    super(message);
  }

  public ErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  public ErrorException(Throwable cause) {
    super(cause);
  }

  public ErrorException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ErrorException(CommonError nbugsCommonError) {
    super(nbugsCommonError.getMsg());
    this.code = nbugsCommonError.getCode();
  }

  public ErrorException(CommonError nbugsCommonError, Throwable cause) {
    super(nbugsCommonError.getMsg(), cause);
    this.code = nbugsCommonError.getCode();
  }

  public Integer getCode() {
    return code;
  }


}
