package com.highgroup.highgroup.task.codeMn.service;

import java.util.List;
import java.util.Map;

public interface CodeMnService {

	// get Code Info
	public List<Map<String,Object>> getCode(Map<String, Object> param) throws Exception;

	// get Code List
	public List<Map<String,Object>> getList(Map<String, Object> param) throws Exception;

	// Code update & Delete
	public List<Map<String,Object>> doModify(Map<String, Object> param) throws Exception;

	// get Addr
	public List<Map<String,Object>> getAddrList(Map<String, Object> param) throws Exception;
	
}
