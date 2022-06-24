package com.highgroup.highgroup.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.highgroup.highgroup.common.model.BaseModel;
import com.highgroup.highgroup.common.model.BodyModel;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;



/**
 * 설 명 :  공통 컨트롤러
 * @author 개발팀 
 * @since 2020.12.09
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일        수정자              수정내용
 *  -------       --------    ---------------------------
 *  2020.12.09     LCY                최초 생성
 * </pre>
 */
@Controller
public class CommonController implements ControllerConstants {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@Nullable
	protected Map<String, Object> commandMap;

	
	public BaseModel setStatus(Object result){
		BodyModel body = new BodyModel();
		body.setBody(result);
		body.setResultCode(0);
		body.setDesc("호출성공");
		return body;
	};

	  
  //Get requestParams in Get
  public static Map<String,Object> getParams(HttpServletRequest request) throws Exception{
    Map<String,Object> map = new HashMap<String,Object>();
    Enumeration<String> paramKeys = request.getParameterNames();
      while (paramKeys.hasMoreElements()) {
          String key = paramKeys.nextElement();
          map.put( key , request.getParameter(key));
      };
    return map;
  }
  //Get RequestBody in Post
  public static Map<String,Object> getBody(HttpServletRequest request) throws IOException {
    String body = null;
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;

    try {
        InputStream inputStream = request.getInputStream();
        if (inputStream != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead = -1;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
            }
        } else {
            stringBuilder.append("");
        }
    } catch (IOException ex) {
        throw ex;
    } finally {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                throw ex;
            }
        }
    }

    body = stringBuilder.toString();
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> map = mapper.readValue(body, Map.class);
    return map;
}



	
	
}
