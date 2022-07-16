package com.cafe.api.system.user;

import com.cafe.api.common.controller.DefaultController;
import com.cafe.api.common.utils.statusType;
import com.cafe.api.config.excepHandler.AppException;
import com.cafe.api.system.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 유저 관리 컨트롤러
 * @author
 * @since 2022.06.03
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2022.06.30  LCY            최초 생성
 *
 *      </pre>
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/user")
public class UserController extends DefaultController {

  @Resource(name = "UserService")
  private UserService userService;


  /**
   * toKen 발급
   * 
   * @param { code_type }
   * @return
   */
  @RequestMapping(value = "/token" , method = RequestMethod.GET)
  public Map<String, Object> getToken() throws Exception {
    Map<String, Object> result = userService.getUserToken(commandMap);
    return result;
  };

}
