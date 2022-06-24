package com.highgroup.highgroup.task.meet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.task.codeMn.service.CodeMnService;
import com.highgroup.highgroup.task.meet.service.MeetService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
/**
 * 모임 컨트롤러
 * @author
 * @since 2021.01.26
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2021.01.26  LCY            최초 생성
 *
 *      </pre>
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/meet")
public class MeetController extends DefaultController {

  @Resource(name = "MeetService")
  private MeetService meetService;
    

  /*모임 생성*/
  @RequestMapping(value = "/create")
  public BaseModel create() throws Exception {
    List<Map<String, Object>> result = meetService.makMeet(commandMap);
    return setStatus(result);
  };
	/*모임 수정*/
  @RequestMapping(value = "/updateMeet")
  public BaseModel updateMeet() throws Exception {
    meetService.updateMeet(commandMap);
    Map<String, Object> data = meetService.getDetail(commandMap);
    return setStatus(data);
  };

  /*모임 List 조회*/
  @RequestMapping(value = "/getList")
  public BaseModel read() throws Exception {
    List<Map<String, Object>> result = meetService.getList(commandMap);
    String totalCnt = meetService.getCount(commandMap);
    Map<String,Object> obj = new HashMap<String,Object>();
    obj.put("resultList", result);
    obj.put("totalCnt", totalCnt);
    return setStatus(obj);
  };

  // 모임 detail
  @RequestMapping(value = "/detail")
  public BaseModel detail() throws Exception {
    Map<String, Object> result = meetService.getDetail(commandMap);
    return setStatus(result);
  };


  /*모임원 조회*/
  @RequestMapping(value = "/memList")
  public BaseModel getMemList() throws Exception {
    List<Map<String, Object>> resultList = meetService.getMemList(commandMap);
    String totalCnt = meetService.getCount(commandMap);
    Map<String,Object> obj = new HashMap<String,Object>();
    obj.put("resultList", resultList);
    obj.put("totalCnt", totalCnt);
    return setStatus(obj);
  };

  /*모임원 가입*/
  @RequestMapping(value = "/signup")
  public BaseModel signup() throws Exception {
    int result = meetService.signup(commandMap);
    return setStatus(result);
  };
  /*멤버 수정*/
  @RequestMapping(value = "/updateMem")
  public BaseModel updateMember() throws Exception {
    int result = meetService.updateMember(commandMap);
    List<Map<String, Object>> resultList = meetService.getMemList(commandMap);
    String totalCnt = meetService.getCount(commandMap);
    Map<String,Object> obj = new HashMap<String,Object>();
    obj.put("resultList", resultList);
    obj.put("totalCnt", totalCnt);
    return setStatus(obj);
  };
  //게시글 리스트
  @RequestMapping(value = "/boardList")
  public BaseModel boardList() throws Exception {
    List<Map<String, Object>> resultList = meetService.getBoardList(commandMap);
    String totalCnt = meetService.getCount(commandMap);
    Map<String,Object> obj = new HashMap<String,Object>();
    obj.put("resultList", resultList);
    obj.put("totalCnt", totalCnt);
    return setStatus(obj);
  };
  

}
