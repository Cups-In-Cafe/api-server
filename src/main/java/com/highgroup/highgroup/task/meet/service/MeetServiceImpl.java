package com.highgroup.highgroup.task.meet.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.dao.DefaultDao;
import com.highgroup.highgroup.common.utils.StringUtil;

import org.springframework.stereotype.Service;


@Service("MeetService")
public class MeetServiceImpl implements MeetService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	
	private String namespace = "meetMapper.";

	
	@Override
	public List<Map<String,Object>> makMeet(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "makMeet");
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};

	@Override
	public int updateMeet(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "updateMeet");
		int result = dao.update(param);
		return result;
	};
	

	@Override
	public List<Map<String,Object>> getList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getList");
		param.put("startRows" , (StringUtil.toInt(param.get("page"),1)-1) * StringUtil.toInt(param.get("count"),10));
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> getBoardList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getBoardList");
		param.put("startRows" , (StringUtil.toInt(param.get("page"),1)-1) * StringUtil.toInt(param.get("count"),10));
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};
	
	@Override
	public List<Map<String,Object>> getMemList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getMemList");
		param.put("startRows" , (StringUtil.toInt(param.get("page"),1)-1) * StringUtil.toInt(param.get("count"),10));
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};

	@Override
	public Map<String,Object> getDetail(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getDetail");
		Map<String,Object> result = dao.select(param);
		return result;
	};
	

	@Override
	public int signup(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "signup");
		int result = dao.insert(param);
		return result;
	};
	
	@Override
	public int updateMember(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "updateMember");
		int result = dao.update(param);
		return result;
	};
	@Override
	public String getCount(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getTotalRows");
		Map<String,Object> rowsCnt = dao.select(param);

		param.put("mId", namespace + "getTotalRows2");
		Map<String,Object> rowsCnt2 = dao.select(param);

		return StringUtil.noNull( rowsCnt.get("totalCnt") );
	};
	
}
