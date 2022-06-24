package com.highgroup.highgroup.common.dao;

import java.util.List;
import java.util.Map;

import com.highgroup.highgroup.common.utils.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("defaultDao")
public class DefaultDao {

  @Autowired
  private SqlSessionTemplate sqlSession;

  public String mapperId(Map<String, Object> param) {
    // 나중에 어떤 아이디가 어떤 query를 실행했는지 이곳에 저장하면 될듯.
    /// testtesttest
    return StringUtil.noNull(param.get("mId"));
  }

  public int selectListCnt(Map<String, Object> param) {

    return sqlSession.selectOne(mapperId(param), param);
  }

  public <E> List<E> selectList(Map<String, Object> param) {
    return sqlSession.selectList(mapperId(param), param);
  }

  public <T> T select(Map<String, Object> param) {
    return sqlSession.selectOne(mapperId(param), param);
  }

  public int insert(Map<String, Object> param) {
    return sqlSession.insert(mapperId(param), param);
  }

  public int update(Map<String, Object> param) {
    return sqlSession.update(mapperId(param), param);
  }


}