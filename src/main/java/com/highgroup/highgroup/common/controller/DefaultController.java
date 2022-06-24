package com.highgroup.highgroup.common.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Modifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.highgroup.highgroup.common.utils.StringUtil;
import com.highgroup.highgroup.config.jwt.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 기본 공통 컨트롤러
 * 
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
 *   2020.12.09  LCY            최초 생성
 *
 *      </pre>
 */
@Configuration
public class DefaultController extends CommonController {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  
  @Value("${app.mode}")
  private String appMode;
  
  Logger logger = LoggerFactory.getLogger(DefaultController.class);
  private final Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.STATIC).create();

  
  @ModelAttribute("hiGroupTop")
  public void init( HttpServletRequest request, HttpServletResponse response,  Map<String,Object> commandMap ) throws Exception {
    //Method
    String method = ((request.getMethod()).toUpperCase()).replaceAll("(!/|\r|\n|&nbsp;)", "");
    
    String Authorization =  StringUtil.noNull( request.getHeader("Authorization") ) ;
    String user_id = !Authorization.equals("")? StringUtil.noNull(jwtTokenUtil.getUserIdFromToken( Authorization )) : null;
    commandMap.put("pUserId",user_id);

    if( method.equals("GET") ){
      commandMap.putAll(getParams(request));
    }else if( method.equals("POST") ){
      commandMap.putAll(getParams(request));
      commandMap.putAll(getBody(request));
    };
    
    logger.info("================================================");
    logger.info(request.getMethod() + " : " + request.getRequestURL());
    logger.info(commandMap.toString());
    logger.info("================================================");

    this.request = request;
    this.response = response;
    this.commandMap = commandMap;
  }
}
