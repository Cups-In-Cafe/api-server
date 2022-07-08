package com.cafe.api.task.user;

import com.cafe.api.config.jwt.JwtTokenUtil;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8",true))
                .alwaysDo(print())
                .build();
    }
    @Test
    @DisplayName("토큰 발급 테스트 - result 성공")
    public void getToken() throws Exception{

        MvcResult result = mockMvc.perform(get("/v1/user/token")
                .param("user_id","crlee")
                .param("user_pwd","123")
        ).andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();

        Gson gson = new Gson();
        Map<String,Object> data = gson.fromJson( content, Map.class );
        String result_auth = data.get("Authorization").toString();

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("user_id","crlee");
        params.put("user_pwd","123");
        String expect_auth = jwtTokenUtil.generateTokenForUser(params);

        Assertions.assertEquals(expect_auth,result_auth );
    }
}