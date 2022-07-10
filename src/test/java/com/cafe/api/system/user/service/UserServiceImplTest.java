package com.cafe.api.system.user.service;

import com.cafe.api.common.utils.statusType;
import com.cafe.api.config.jwt.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@AutoConfigureMockMvc
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @Test
    @DisplayName("토큰 발급 테스트 - result 성공")
    public void getUserToken() throws Exception{

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("user_id","crlee");
        params.put("user_pwd","123");

        Map<String,Object> result = userService.getUserToken(params);

        Map<String,Object> auth_param = new HashMap<String,Object>();
        auth_param.put("user_id","crlee");
        auth_param.put("user_pwd","123");
        String expect_auth = jwtTokenUtil.generateTokenForUser(auth_param);
        Assertions.assertEquals(expect_auth,result.get("Authorization").toString() );
    }

    @Test
    @DisplayName("토큰 발급 테스트 - parameter user_pwd 누락")
    public void getUserToken2() throws Exception{

        Map<String,Object> params2 = new HashMap<String,Object>();
        params2.put("user_id","crlee");
        Map<String,Object> result2 = userService.getUserToken(params2);
        Assertions.assertEquals( statusType.no_arg_user_pwd ,(statusType) result2.get("status"));
    }

    @Test
    @DisplayName("토큰 발급 테스트 - parameter user_id 누락")
    public void getUserToken3() throws Exception{
        Map<String,Object> params2 = new HashMap<String,Object>();
        params2.put("user_id","crlee");
        Map<String,Object> result2 = userService.getUserToken(params2);
        Assertions.assertEquals( statusType.no_arg_user_pwd ,(statusType) result2.get("status"));
    }
}