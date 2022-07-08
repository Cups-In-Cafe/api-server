package com.cafe.api.task.codeMn;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import com.cafe.api.common.controller.DefaultController;
import com.cafe.api.common.controller.error.AppException;
import com.cafe.api.common.controller.error.statusType;
import com.cafe.api.common.utils.StringUtil;
import com.cafe.api.task.codeMn.service.CodeMnService;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
/**
 * 코드 관리 컨트롤러
 * @author
 * @since 2020.12.09
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2021.01.12  LCY            최초 생성
 *
 *      </pre>
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/code")
public class CodeMnController extends DefaultController {

  @Resource(name = "CodeMnService")
  private CodeMnService codeService;


  /**
   * code 조회
   * 
   * @param { code_type }
   * @return
   */
  @RequestMapping(value = "" , method = RequestMethod.GET)
  public Map<String,Object> getCode() throws Exception {
    //List<Map<String, Object>> result = codeService.getCode(commandMap);
    //return setStatus(result);
    Map<String,Object> obj = new HashMap<String,Object>();
    obj.put("name", StringUtil.noNull(commandMap.get("name") , "Hellow SpringBoot"));
    return obj;
  };


}
