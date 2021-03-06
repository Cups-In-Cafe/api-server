package com.cafe.api.config.excepHandler;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cafe.api.common.controller.DefaultController;
import com.cafe.api.common.utils.StringUtil;
import com.cafe.api.common.utils.statusType;
import com.cafe.api.config.excepHandler.service.ExceptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends DefaultController {

  @Resource(name = "exceptionService")
  private ExceptionService exceptionService;

  @ExceptionHandler({ AppException.class })
  public ResponseEntity<Map<String,Object>> handleApp(String request, AppException e) throws Exception  {

    statusType errorType = e.getErrorType();

    Map<String,Object> result = new HashMap<String,Object>();
    result.put("status",errorType);
    return new ResponseEntity<>(result, errorType.httpStatus);
  }
  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Map<String,Object>> handleAll(HttpServletRequest request, Exception ex) throws Exception {

    Map<String,Object> result = new HashMap<String,Object>();
    result.put("status",statusType.internal_error);
    result.put("error", StringUtil.noNull( ex.getMessage() ));

    commandMap.put("type",statusType.internal_error.errorCode);
    commandMap.put("content", StringUtil.noNull( ex.getMessage() ));
    exceptionService.insertLog(commandMap);

    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
