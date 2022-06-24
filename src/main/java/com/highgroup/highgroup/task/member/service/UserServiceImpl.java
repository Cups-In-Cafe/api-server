package com.highgroup.highgroup.task.member.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.controller.error.AppException;
import com.highgroup.highgroup.common.controller.error.ErrorType;
import com.highgroup.highgroup.common.dao.DefaultDao;
import com.highgroup.highgroup.common.utils.StringUtil;
import com.highgroup.highgroup.config.jwt.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.ParamTag;

@Service("UserService")
public class UserServiceImpl implements UserService {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Resource(name = "defaultDao")
  private DefaultDao dao;

  private String namespace = "userMapper.";

	
	@Override
	public Map<String,Object> getUserToken(Map<String, Object> param) throws Exception {
		
		param.put("user_pw", StringUtil.sha256(param.get("user_pw")) );
		param.put("mId", namespace + "selectUser");
		Map<String,Object> result = dao.select(param);
		if( result !=  null){
			param.put("mId", namespace + "insertUserLog");	
			dao.selectList(param);
			result.put("Authorization" , jwtTokenUtil.generateTokenForUser(param) );
		} else {
			throw new AppException(ErrorType.user_auth_fail, null);
		};
		
		return result;
	};
	@Override
	public Map<String,Object> getDupChk(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "setDupChk");
		Map<String,Object> result = dao.select(param);
		return result;
	};
	
	@Override
	public int setMember(Map<String, Object> param) throws Exception {
		param.put("user_pw", StringUtil.sha256(param.get("user_pw")) );
		param.put("mId", namespace + "insertUser");
		int result = dao.insert(param);
		param.put("mId", namespace + "insertUserAttr");
		dao.insert(param);
		return result;
	}

	@Override
	public Map<String, Object> getUserInfo(Map<String, Object> params) throws Exception {
		params.put("mId", namespace + "selectUser");
		Map<String, Object> result = dao.select(params);
		return result;
	}

	@Override
	public Map<String, Object> findId(Map<String, Object> params) {
		params.put("mId", namespace + "selectUserId");
		Map<String, Object> result = dao.select(params);
		return result;
	}

	//패스워드초기화시
	@Override
	public int passUpdate(Map<String, Object> param) {
		param.put("mId", namespace + "selectUser");
		if(param.get("user_pw") != null){
			param.put("user_pw", StringUtil.sha256(param.get("user_pw")) );
		}
		Map<String, Object> adminInfo = dao.select(param);
	
		if (adminInfo == null) {
			throw new AppException(ErrorType.auth_fail, null);
		} else {
			param.put("mId", namespace + "updateUser");
			param.put("user_pw", StringUtil.sha256(param.get("change_pw")) );
		  	return(dao.update(param));
		}

	}
  
	@Override
	public int userDelete(Map<String, Object> param) {
		param.put("mId", namespace + "userDelete");
		param.put("user_pw",StringUtil.sha256(param.get("user_pw")) );
		int result = dao.update(param);
		if (result != 1) {
			throw new AppException(ErrorType.user_no_password, null);
		  }
		return result;
	};

	@Override
	public List<Map<String,Object>> getUserList(Map<String, Object> param) {
		param.put("mId", namespace + "selectUserList");
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	}
	
	@Override
	public int changeState(Map<String, Object> param) {
		param.put("mId", namespace + "updateUser");
		System.out.println("checkcheck"+param);
		int result = dao.update(param);
		System.out.println("return value"+result);
		if(result != 1){
			//throw new AppException(ErrorType.) error
			}
		return result;
	};

}