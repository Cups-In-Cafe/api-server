package com.highgroup.highgroup.task.profile.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ProfileService {

  // 닉네임 중복 체크
  public Map<String,Object> nickCheck(Map<String, Object> param) throws Exception;
  // 프로필 등록 및 수정
  public List<Map<String, Object>> setProfile(HttpServletRequest request , Map<String, Object> param) throws Exception;
  // 프로필 조회
  public List<Map<String, Object>> viewProfile(Map<String, Object> param) throws Exception;
  
}
