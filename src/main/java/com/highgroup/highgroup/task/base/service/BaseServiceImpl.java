package com.highgroup.highgroup.task.base.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.dao.DefaultDao;

import org.springframework.stereotype.Service;


@Service("BaseService")
public class BaseServiceImpl implements BaseService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	
	@Override
	public List<Map<String,Object>> callProc(Map<String, Object> param) throws Exception {
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};

	

}
