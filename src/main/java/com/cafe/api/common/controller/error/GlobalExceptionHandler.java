package com.cafe.api.common.controller.error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cafe.api.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  //@Resource(name = "exceptionService")
  //private ExceptionService exceptionService;

  @ExceptionHandler({ AppException.class })
  public ResponseEntity<Map<String,Object>> handleApp(HttpServletRequest request, AppException e) {

    statusType errorType = e.getErrorType();

    Map<String,Object> result = new HashMap<String,Object>();
    result.put("status",errorType);
    return new ResponseEntity<>(result, errorType.httpStatus);
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Map<String,Object>> handleAll(HttpServletRequest request, Exception ex) {

    Map<String,Object> result = new HashMap<String,Object>();
    result.put("status",statusType.internal_error);
    result.put("error", StringUtil.noNull( ex.getMessage() ));

    //exceptionService.insertErr(errMap);

    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
