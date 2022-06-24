package com.highgroup.highgroup.task.base;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.task.base.service.BaseService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController extends DefaultController {

  Logger logger = LoggerFactory.getLogger(BaseController.class);

  @Resource(name = "BaseService")
  private BaseService baseService;

  /*
  { mId : 'testMapper.callTestProc', test1:'a', test2:'call', test3:'333' }  -> case 1
  { mId : 'testMapper.callTestProc2', test1:'a' }  -> case 1
  */
  @RequestMapping(value = "/base/task")
  public BaseModel callProc() throws Exception {
    List<Map<String, Object>> result = baseService.callProc(commandMap);
    return setStatus(result);
  };

  

}
