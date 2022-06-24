package com.highgroup.highgroup.task.profile.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.highgroup.highgroup.common.dao.DefaultDao;
import com.highgroup.highgroup.common.utils.StringUtil;

import org.springframework.stereotype.Service;


@Service("ProfileService")
public class ProfileServiceImpl implements ProfileService {

	@Resource(name="defaultDao")
	private DefaultDao dao;
	private String namespace = "profileMapper.";

	@Override
	public Map<String,Object> nickCheck(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "chkNickDup");
		Map<String,Object> result = dao.select(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> setProfile(HttpServletRequest request , Map<String, Object> param) throws Exception {
		param.put("mId", namespace + "setUserProfile");
		//배열 변환
		String[] I_ARR = request.getParameterValues("i_type");
		String[] L_ARR = request.getParameterValues("l_id");
		String[] P_ARR = request.getParameterValues("p_id");
		String pkg = "&";
		param.put("I_ARR", StringUtil.arrayJoin(pkg , I_ARR) );
		param.put("L_ARR", StringUtil.arrayJoin(pkg , L_ARR) );
		param.put("P_ARR", StringUtil.arrayJoin(pkg , P_ARR) );
		param.put("pkg", pkg);
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};
	@Override
	public List<Map<String,Object>> viewProfile(Map<String, Object> param) throws Exception {
		param.put("mId", namespace + param.get("queryId") );
		List<Map<String,Object>> result = dao.selectList(param);
		return result;
	};

}
