package com.cafe.api.config.excepHandler.service;

import java.util.List;
import java.util.Map;

public interface ExceptionService {

	// 에러 Log insert
	public int insertLog(Map<String, Object> param) throws Exception;

}
