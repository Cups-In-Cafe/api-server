package com.highgroup.highgroup.common.controller.error;

import org.springframework.http.HttpStatus;

public enum ErrorType {

  internal_error(-10001, "서버 내부 에러"), 

  //user
  user_auth_fail(10000, "회원 인증에 실패하였습니다."),
  user_no_registered(10001, "등록된 정보가 없습니다."),
  already_user(10002, "이미 가입된 회원입니다."),
  user_email_duplicate(10003, "이미 사용중인 이메일입니다."),
  user_no_password(10004, "비밀번호가 일치하지 않습니다."),

  //auth
  token_parse_error(20001, "유효하지 않은 토큰 입니다."),
  fail_to_access(20002, "잘못된 접근입니다."),
  do_not_have_authority(20003, "권한이 없습니다."),

  //admin
  auth_fail(30000, "회원 인증에 실패하였습니다."),
  no_registered_user(30001, "등록된 정보가 없습니다."),

  //common
  invalidate_request_parameter(40000, "파라미터가 유효하지 않습니다."),
  missing_request_parameter(40001, "필수 파라미터가 누락되었습니다.");

  public int errorCode;
  public String message;
  public HttpStatus httpStatus;

  ErrorType(int errorCode, String message, HttpStatus httpStatus) {
    this.errorCode = errorCode;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  ErrorType(int errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
    this.httpStatus = HttpStatus.OK;
  }
}
