package com.cafe.api.task.user.service;

import com.cafe.api.common.controller.error.statusType;
import com.cafe.api.common.dao.DefaultDao;
import com.cafe.api.common.utils.StringUtil;
import com.cafe.api.config.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
		if( StringUtil.noNull( param.get("user_id") ) == ""){
			result.put("status" , statusType.no_arg_user_id );
			return result;
		}
		if( StringUtil.noNull( param.get("user_pwd") ) == ""){
			result.put("status" , statusType.no_arg_user_pwd );
			return result;
		}

		param.put("user_pwd", StringUtil.sha256(param.get("user_pwd")) );
		param.put("mId", namespace + "selectUser");
		//Map<String,Object> result = dao.select(param);
		if( result == null){
			result.put("status" , statusType.auth_fail );
			return result;
		}
		result.put("Authorization" , jwtTokenUtil.generateTokenForUser(param) );
		result.put("status" , statusType.auth_success );
		return result;

	};
	
}
