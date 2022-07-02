package com.cafe.api.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringUtilTest {

    @Test
    @DisplayName("noNull(String)")
    void noNull_String() {
        String input = "crlee";
        String result = StringUtil.noNull(input );
        Assertions.assertEquals( result , input);
    }
    @Test
    @DisplayName("noNull(null)")
    void noNull_null() {
        String input = null;
        String result = StringUtil.noNull(input);
        Assertions.assertEquals( result , "" );
    }
    @Test
    @DisplayName("noNull(null,default)")
    void noNull_default() {
        String input = null;
        String result = StringUtil.noNull(input,"crlee");
        Assertions.assertEquals( result ,"crlee");
    }
}