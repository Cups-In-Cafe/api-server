package com.cafe.api.task.user.service;

import com.cafe.api.common.controller.error.AppException;
import com.cafe.api.common.controller.error.ErrorType;
import com.cafe.api.common.dao.DefaultDao;
import com.cafe.api.common.utils.StringUtil;
import com.cafe.api.config.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("UserService")
public class UserServiceImpl implements UserService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	private String namespace = "userMapper.";
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Override
	public Map<String,Object> getUserToken(Map<String, Object> param) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		if( StringUtil.noNull( param.get("user_pwd") ) == "" || StringUtil.noNull( param.get("user_id") ) == "" ){
			result.put("msg" , ErrorType.user_auth_fail.message );
			return result;
		}
		param.put("user_pwd", StringUtil.sha256(param.get("user_pwd")) );
		param.put("mId", namespace + "selectUser");
		//Map<String,Object> result = dao.select(param);
		result.put("test",true);
		if( result == null){
			result.put("msg" , ErrorType.user_auth_fail.message );
			return result;
		}
		result.put("Authorization" , jwtTokenUtil.generateTokenForUser(param) );
		result.put("msg" , ErrorType.auth_success.message );
		return result;

	};
	
}
