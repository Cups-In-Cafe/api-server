package com.highgroup.highgroup.task.base.service;

import java.util.List;
import java.util.Map;

public interface BaseService {

	public List<Map<String,Object>> callProc(Map<String, Object> param) throws Exception;

}
