package com.highgroup.highgroup.task.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.highgroup.highgroup.common.controller.DefaultController;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.task.member.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
public class AdminController extends DefaultController {

  Logger logger = LoggerFactory.getLogger(AdminController.class);

  @Resource(name = "AdminService")
  private AdminService adminService;

  @RequestMapping(value = "/login")
  public BaseModel login() throws Exception {
    String adminToken = adminService.getAdminToken(commandMap);
    return setStatus(adminToken);
  };

  @RequestMapping(value = "/info")
  public BaseModel adminInfo() throws Exception{
    Map <String, Object> adminInfo = adminService.getAdminInfo(commandMap);
    return setStatus(adminInfo);
  }
  
  @RequestMapping(value = "/register")
  public BaseModel insert() throws Exception {    
    return setStatus(adminService.insertMember(commandMap));
  };
}
