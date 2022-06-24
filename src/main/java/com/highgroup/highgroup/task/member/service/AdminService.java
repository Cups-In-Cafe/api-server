package com.highgroup.highgroup.task.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

  public String getAdminToken(Map<String, Object> param) throws Exception;
  
  public List<Map<String, Object>> getMember(Map<String, Object> param) throws Exception;

  public int insertMember(Map<String, Object> param) throws Exception;
  
  public Map<String, Object> getAdminInfo(Map<String, Object> params) throws Exception;
}
