package com.cafe.api.task.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cafe.api.common.controller.error.AppException;
import com.cafe.api.common.controller.error.ErrorType;
import com.cafe.api.common.dao.DefaultDao;
import com.cafe.api.common.utils.StringUtil;
import com.cafe.api.config.jwt.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  
  @Resource(name = "defaultDao")
  private DefaultDao dao;

  private String namespace = "adminMapper.";

  @Override
  public List<Map<String, Object>> getMember(Map<String, Object> param) throws Exception {
    param.put("mId", namespace + "selectAdmin");
    List<Map<String, Object>> result = dao.selectList(param);
    return result;
  };

  @Override
  public int insertMember(Map<String, Object> param) throws Exception {
    String password = StringUtil.sha256(param.get("admin_pwd"));
    param.put("admin_pwd", password);
    param.put("mId", namespace + "insertAdmin");
    int result = dao.insert(param);
    return result;
  }

  //토큰얻기.
  @Override
  public String getAdminToken(Map<String, Object> param) throws Exception {
    String password = StringUtil.sha256(param.get("user_pwd"));
    param.put("user_pwd", password);
    param.put("mId", namespace + "adminSelectById");

    Map<String, Object> admin = dao.select(param);
    String result = null;
    if (admin != null){
      result = jwtTokenUtil.generateTokenForAdmin(param);
    } else {
      throw new AppException(ErrorType.auth_fail, null);
    }
    return result;
  }

  @Override
  public Map<String, Object> getAdminInfo(Map<String, Object> params) throws Exception{
    params.put("mId", namespace + "adminSelectById");
    Map<String, Object> adminInfo = dao.select(params);
    return adminInfo;
  }

  
}
