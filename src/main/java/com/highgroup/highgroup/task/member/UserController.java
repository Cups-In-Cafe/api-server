package com.highgroup.highgroup.task.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.controller.error.AppException;
import com.highgroup.highgroup.common.controller.error.ErrorType;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.common.utils.StringUtil;
import com.highgroup.highgroup.common.utils.SmsMessage;
import com.highgroup.highgroup.task.member.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/v1/user")
public class UserController extends DefaultController {

  @Resource(name = "UserService")
  private UserService userService;

  @Autowired
  private SmsMessage smsMsg;

  //전화번호 인증번호 체크
  @RequestMapping(value = "/telChk")
  public BaseModel telChk() throws Exception {

    String randomNum = StringUtil.getRandomNum(6);
    String toNum = StringUtil.noNull(commandMap.get("toNum"));
    String content = "Hi SNS 인증번호 ["+randomNum+"] 를 입력해주세요.";
    
    String returnMsg = smsMsg.sendMessage( toNum , content );
    HashMap<String,Object> result = new HashMap<String,Object>();
    result.put("randomNum", randomNum );
    result.put("smsReturn", returnMsg );

    return setStatus(result);
    
  };

  //로그인
  @RequestMapping(value = "/login")
  public BaseModel login() throws Exception {
    Map<String, Object> result = userService.getUserToken(commandMap);
    return setStatus(result);
  };

  //회원가입
  @RequestMapping(value = "/signup")
  public BaseModel signup() throws Exception {

    Map<String, Object> dupChk = userService.getDupChk(commandMap);
    int result = 0;

    if(dupChk == null){
      result = userService.setMember(commandMap);
    }else{
      throw new AppException(ErrorType.user_email_duplicate, null);
    };

    return setStatus(result);
  };

  //user정보
  @RequestMapping(value = "/info")
  public BaseModel adminInfo() throws Exception{
    Map <String, Object> result = userService.getUserInfo(commandMap);
    return setStatus(result);
  }

  //아이디찾기
  @RequestMapping(value = "/find_id")
  public BaseModel findId() throws Exception{
    Map <String, Object> result = userService.findId(commandMap);
    System.out.println("commandMap"+commandMap);
    return setStatus(result);
  }

  //비밀번호변경(post)
  @RequestMapping(value = "/pass_update")
  public BaseModel passCheck() throws Exception {
    int result = userService.passUpdate(commandMap);
    return setStatus(result);
  }

  //아이디탈퇴(get)
  @RequestMapping(value = "/delete")
  public BaseModel userDelete() throws Exception {
    return setStatus(userService.userDelete(commandMap));
  }
 
  //유저리스트
  @RequestMapping(value = "/generallist")
  public BaseModel userList() throws Exception {
    List<Map<String,Object>> result = userService.getUserList(commandMap);
    return setStatus(result);
  }

  //유저 승인
  @RequestMapping(value = "/approve_state")
  public BaseModel changeState() throws Exception{
    int result = userService.changeState(commandMap);
    return setStatus(result);
  }
}
