package com.highgroup.highgroup.task.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	public String getCount(Map<String, Object> param) throws Exception ;
	public Map<String,Object> getBoard(Map<String, Object> param) throws Exception ;
	public List<Map<String,Object>> getReplyList(Map<String, Object> param) throws Exception ;
	public List<Map<String,Object>> getBoardList(Map<String, Object> param) throws Exception ;
	public int insertBoard(Map<String, Object> param) throws Exception ;
	public int updateBoard	(Map<String, Object> param) throws Exception ;
	
	
}
