package com.modelgarden.unittest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

public class ModelTest extends AbstractTestNGTest
{
    @Test
    public void loginTest()
    {
        try
        {
            // 注册
            MvcResult re = mockMvc.perform((post("/updateModelInfo")
                   .param("uid", "13951744261")
                   .param("realname", "zhangfeng")
                   .param("nickname", "zfnuaa")
                   .param("identityid", "371311198511272312")
                   .param("birthDay", "1985-11-27")
                   .param("age", "18")
                    ))
                   .andReturn();
            
            String resStr = re.getResponse().getContentAsString();
            System.out.println(resStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void getModelInfoTest()
    {
        try
        {
            // 注册
            MvcResult re = mockMvc.perform((post("/getModelDetailInfo")
                   .param("modelID", "13951744261")
                    ))
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
