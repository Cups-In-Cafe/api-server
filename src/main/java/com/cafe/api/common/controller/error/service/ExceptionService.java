package com.cafe.api.common.controller.error.service;

import java.util.List;
import java.util.Map;

public interface ExceptionService {

	// 에러 insert
	public int insertErr(Map<String, Object> param) throws Exception;

}
