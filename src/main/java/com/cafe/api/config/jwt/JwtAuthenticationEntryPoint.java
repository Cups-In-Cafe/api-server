package com.cafe.api.config.jwt;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe.api.common.model.BodyModel;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException {

      response.setContentType("application/json;charset=UTF-8");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

      Map<String,Object> _data = new HashMap<String,Object>();
      _data.put("msg", authException.getMessage());

      JSONObject responseJson = new JSONObject();
      responseJson.put("resultCode", 0);
      responseJson.put("status", "호출성공" );
      responseJson.put("data", _data );

      response.getWriter().print(responseJson);

  }
}
