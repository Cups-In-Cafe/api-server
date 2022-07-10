package com.cafe.api.system.user.service;

import java.util.Map;

public interface UserService {

	// get Token
	public Map<String,Object> getUserToken(Map<String, Object> param) throws Exception;

	
}
