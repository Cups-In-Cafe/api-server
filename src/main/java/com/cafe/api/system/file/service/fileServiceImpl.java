package com.cafe.api.system.file.service;

import java.util.Map;
import javax.annotation.Resource;

import com.cafe.api.common.dao.DefaultDao;

import org.springframework.stereotype.Service;


@Service("fileService")
public class fileServiceImpl implements fileService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	
	private String namespace = "fileMapper.";

	@Override
	public int insertFile(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "insertFile" );
		return dao.insert(param);
	};

	

}
