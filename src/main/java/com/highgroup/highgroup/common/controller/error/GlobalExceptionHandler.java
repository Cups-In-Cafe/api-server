package com.highgroup.highgroup.common.controller.error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import com.highgroup.highgroup.common.model.BaseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private Logger log = LoggerFactory.getLogger("error");

  @ExceptionHandler({ AppException.class })
  @ResponseBody
  public ResponseEntity<BaseModel> handleApplicationErrorException(HttpServletRequest request, AppException e) {

    ErrorType errorType = e.getErrorType();

    BaseModel res = new BaseModel();
    res.setResultCode(errorType.errorCode);
    res.setDesc(errorType.message);

    return new ResponseEntity<>(res, errorType.httpStatus);
  }

  @ExceptionHandler({ Exception.class })
  @ResponseBody
  public ResponseEntity<BaseModel> notFoundHandler(HttpServletRequest request, Exception ex) {

    ex.printStackTrace();

    StringWriter sw = new StringWriter();
    ex.printStackTrace(new PrintWriter(sw));

    BufferedReader br = new BufferedReader(new StringReader(sw.toString()));
    StringBuffer desc = new StringBuffer();
    try {
      for (int i = 0; i < 3; i++) {
        desc.append(br.readLine());
        if (i == 2) {
          desc.append("...");
        } else {
          desc.append("\r\n");
        }
      }
    } catch (IOException e) {
      desc.append(ex.toString());
    }

    BaseModel res = new BaseModel();
    res.setResultCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    res.setDesc(desc.toString());

    return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
