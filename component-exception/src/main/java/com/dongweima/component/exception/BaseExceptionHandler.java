package com.dongweima.component.exception;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * 1.捕获参数错误类异常,定义自己的返回
 *
 * 2.定义业务异常捕获,打印warn日志
 *
 * 3.打印error级别异常,打印error级别日志.
 */
@ControllerAdvice
public class BaseExceptionHandler {

  private final static Logger log = LoggerFactory.getLogger(BaseExceptionHandler.class);

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handle404Error(WebRequest request, BusinessException e) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;
    log.warn(logLocalErrorMsg(e, request), e);
    return new ResponseEntity<Object>(getErrorBody((LocalError) e), headers, status);
  }

  @ExceptionHandler(ErrorException.class)
  public ResponseEntity<Object> handleError(WebRequest request, ErrorException e) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;
    log.error(logLocalErrorMsg(e, request), e);
    return new ResponseEntity<Object>(getErrorBody((LocalError) e), headers, status);
  }

  @ExceptionHandler({Exception.class})
  public final ResponseEntity<Object> handleException(Exception e, WebRequest request) {
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.OK;
    log.error("Unknown exception type:{} ", e.getClass().getName(), e);
    return new ResponseEntity<>(getErrorBody(e), headers, status);
  }

  private Map<String, Object> getErrorBody(LocalError e) {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("code", e.getCode());
    result.put("msg", e.getMessage());
    return result;
  }

  private Map<String, Object> getErrorBody(Exception e) {
    Map<String, Object> result = new HashMap<String, Object>();
    result.put("code", CommonError.DEFAULT.getCode());
    result.put("msg", e.getMessage());
    return result;
  }

  private String logLocalErrorMsg(LocalError error, WebRequest webRequest) {
    StringBuilder errMsg = new StringBuilder();
    if (webRequest instanceof ServletWebRequest) {
      ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
      HttpServletRequest servletRequest = servletWebRequest.getRequest();
      if (servletRequest != null) {
        errMsg.append("url:");
        errMsg.append(servletRequest.getRequestURL().toString());
        errMsg.append(",");
      }
    }
    errMsg.append("code:");
    errMsg.append(error.getCode());
    errMsg.append(",message:");
    errMsg.append(error.getMessage());
    return errMsg.toString();
  }
}
