package com.highgroup.highgroup.task.board.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.dao.DefaultDao;
import com.highgroup.highgroup.common.utils.StringUtil;

import org.springframework.stereotype.Service;


@Service("BoardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	
	private String namespace = "boardMapper.";

	@Override
	public String getCount(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getTotalRows");
		Map<String,Object> rowsCnt = dao.select(param);
		return StringUtil.noNull( rowsCnt.get("totalCnt") );
	};
	@Override
	public Map<String,Object> getBoard(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getBoard");
		Map<String,Object> result = dao.select(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> getReplyList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getReplyList");
		param.put("startRows" , (StringUtil.toInt(param.get("page"),1)-1) * StringUtil.toInt(param.get("count"),10));
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> getBoardList(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "getBoardList");
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};

	@Override
	public int insertBoard(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "insertBoard");
		int result = dao.insert(param);
		return result;
	};
	@Override
	public int updateBoard	(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "updateBoard	");
		int result = dao.insert(param);
		return result;
	};
	
	
}
