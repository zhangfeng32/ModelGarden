package com.modelgarden.unittest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

public class UserLoginTest extends AbstractTestNGTest
{
    @Test
    public void loginTest()
    {
        try
        {
            // 注册
            MvcResult re = mockMvc.perform((post("/login").param("username", "13951744261")
                   .param("password", "e10adc3949ba59abbe56e057f20f883e")))
                   .andReturn();
            
            String resStr = re.getResponse().getContentAsString();
            System.out.println(resStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
