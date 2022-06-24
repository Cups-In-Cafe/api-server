package com.highgroup.highgroup.task.meet.service;

import java.util.List;
import java.util.Map;

public interface MeetService {

	
	public List<Map<String,Object>> makMeet(Map<String, Object> param) throws Exception ;
	public int updateMeet(Map<String, Object> param) throws Exception ;
	public List<Map<String,Object>> getList(Map<String, Object> param) throws Exception ;
	public List<Map<String,Object>> getMemList(Map<String, Object> param) throws Exception ;
	public List<Map<String,Object>> getBoardList(Map<String, Object> param) throws Exception ;
	

	public Map<String,Object> getDetail(Map<String, Object> param) throws Exception ;
	public int signup(Map<String, Object> param) throws Exception ;
	public int updateMember(Map<String, Object> param) throws Exception ;
	public String getCount(Map<String, Object> param) throws Exception ;
	
	
	
	
}
