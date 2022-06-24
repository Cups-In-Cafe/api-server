package com.highgroup.highgroup.task.codeMn.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.dao.DefaultDao;
import com.highgroup.highgroup.common.utils.StringUtil;

import org.springframework.stereotype.Service;


@Service("CodeMnService")
public class CodeMnServiceImpl implements CodeMnService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	
	private String namespace = "codeMnMapper.";

	@Override
	public List<Map<String,Object>> getCode(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getCode");
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> getList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getList");
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> doModify(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "doModify");
		dao.selectList(param);
		List<Map<String,Object>> result = getList(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> getAddrList(Map<String, Object> param) throws Exception {
		
		String QueryId =  StringUtil.noNull( param.get("type") ).equals("addr_cd")  ? "getAddrCd" : "getAddrNm";
		param.put("mId", namespace + QueryId);

		dao.selectList(param);
		List<Map<String,Object>> result = getList(param);
		return result;
	};
	
}
