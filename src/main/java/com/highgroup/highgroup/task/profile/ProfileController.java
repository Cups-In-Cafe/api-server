package com.highgroup.highgroup.task.profile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.task.profile.service.ProfileService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/v1/profile")
public class ProfileController extends DefaultController {


  @Resource(name = "ProfileService")
  private ProfileService profileService;

  /*닉네임 중복 체크*/
  @RequestMapping(value = "/nickChk")
  public BaseModel nickName_Check() throws Exception {
    Map<String,Object> result = profileService.nickCheck(commandMap);
    return setStatus(result);
  };

  //프로필 생성 및 수정
  @RequestMapping(value = "/set")
  public BaseModel setProfile() throws Exception {
    List<Map<String, Object>> result = profileService.setProfile(request,commandMap);
    return setStatus(result);
  };
  //프로필 조회
  @RequestMapping(value = "/view")
  public BaseModel viewProfile() throws Exception {
    commandMap.put("queryId", "viewProfile");
    List<Map<String, Object>> profile = profileService.viewProfile(commandMap);
    commandMap.put("queryId", "viewInterest");
    List<Map<String, Object>> interest = profileService.viewProfile(commandMap);
    commandMap.put("queryId", "viewPersonal");
    List<Map<String, Object>> personal = profileService.viewProfile(commandMap);
    commandMap.put("queryId", "viewLocation");
    List<Map<String, Object>> location = profileService.viewProfile(commandMap);
    
    Map<String, Object> result = new HashMap<String,Object>();
    result.put("profile", profile);
    result.put("interest", interest);
    result.put("personal", personal);
    result.put("location", location);
    return setStatus(result);
  };

}
