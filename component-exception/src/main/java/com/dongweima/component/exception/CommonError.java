package com.dongweima.component.exception;

public enum CommonError implements LocalError {
  SUCCESS(0, "成功"),
  PARAMETER_ERROR(400, "参数错误"),
  UNAUTHORIZED_ERROR(401, "认证失败"),
  ACCESS_DENIED(403, "访问权限受限"),
  DATABASE_ERROR(5000, "数据库错误"),
  SIGN_KEY_ERROR(5001, "签名错误"),
  ACCESS_FALLBACK(5002, "访问降级"),
  UNEXPECTED_ERROR(6000, "未知错误"),
  RPC_ERROR(7777, "rpc服务异常"),
  DEFAULT(10000, "rpc服务异常");

  private int code;
  private String msg;

  CommonError(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return msg;
  }

  public String getMsg() {
    return this.msg;
  }

  public static CommonError find(int code) {
    CommonError[] codes = values();
    for (CommonError cmd : codes) {
      if (cmd.getCode() == code) {
        return cmd;
      }
    }
    return UNEXPECTED_ERROR;
  }
}