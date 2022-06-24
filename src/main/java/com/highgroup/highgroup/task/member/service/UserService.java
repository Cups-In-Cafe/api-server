package com.highgroup.highgroup.task.member.service;

import java.util.List;
import java.util.Map;

public interface UserService {

  /**
   * 멤버 가저오기
   */
  public Map<String,Object> getUserToken(Map<String, Object> param) throws Exception;

  /**
   * 멤버 중복 체크
   */
  public Map<String,Object> getDupChk(Map<String, Object> param) throws Exception;
  
  /**
   * 멤버 등록
   */
  public int setMember(Map<String, Object> param) throws Exception;
  
  public Map<String, Object> getUserInfo(Map<String, Object> params) throws Exception;

public Map<String, Object> findId(Map<String, Object> params);

public int userDelete(Map<String, Object> param);

public int passUpdate(Map<String, Object> param);

public List<Map<String,Object>> getUserList(Map<String, Object> param);

public int changeState(Map<String, Object> param);

}
