package com.cafe.api.task.user;

import com.cafe.api.common.controller.error.ErrorType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.xml.crypto.Data;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

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

        String result_msg = data.get("msg").toString();
        String expect_msg = ErrorType.auth_success.message;
        Assertions.assertEquals(expect_msg,result_msg );
    }
}