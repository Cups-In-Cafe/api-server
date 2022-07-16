package com.cafe.api.config.excepHandler.service;

import java.util.Map;
import javax.annotation.Resource;

import com.cafe.api.common.dao.DefaultDao;
import org.springframework.stereotype.Service;


@Service("exceptionService")
public class ExceptionServiceImpl implements ExceptionService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	
	private String namespace = "logMapper.";

	@Override
	public int insertLog(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "insertLog" );
		return dao.insert(param);
	};

	

}
