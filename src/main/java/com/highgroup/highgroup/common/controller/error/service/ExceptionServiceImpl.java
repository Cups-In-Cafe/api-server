package com.highgroup.highgroup.common.controller.error.service;

import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.dao.DefaultDao;

import org.springframework.stereotype.Service;


@Service("exceptionService")
public class ExceptionServiceImpl implements ExceptionService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	
	private String namespace = "commonMapper.";

	@Override
	public int insertErr(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "insertErr" );
		return dao.insert(param);
	};

	

}
