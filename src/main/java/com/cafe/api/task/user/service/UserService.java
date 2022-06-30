package com.cafe.api.task.user.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	// get Token
	public Map<String,Object> getUserToken(Map<String, Object> param) throws Exception;

	
}
