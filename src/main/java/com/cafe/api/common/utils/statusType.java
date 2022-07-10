package com.cafe.api.common.utils;

import org.springframework.http.HttpStatus;

public enum statusType {

  internal_error(-10001, "서버 내부 에러"),
  api_success(10002, "통신 성공"),
  fail_success(10003, "통신 실패",HttpStatus.BAD_REQUEST),
  fail_to_access(10004, "잘못된 접근"),
  auth_fail(20000, "인증 실패"),
  auth_success(20001, "인증 성공"),

  no_arg_user_id(50000,"회원 ID 누락"),
  no_arg_user_pwd(50000,"회원 비밀번호 누락");

  //common

  public int errorCode;
  public String message;
  public HttpStatus httpStatus;

  statusType(int errorCode, String message, HttpStatus httpStatus) {
    this.errorCode = errorCode;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  statusType(int errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
    this.httpStatus = HttpStatus.OK;
  }
}
