package com.modelgarden.unittest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.After;
import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.test.web.servlet.MvcResult;

import com.modelgarden.common.RoleType;

public class UserRegisterTest extends AbstractTestNGTest
{
  
    @Test
    public void registerTest()
    {
        try
        {
            // 生成验证码
            mockMvc.perform(post("/getVerifyCode").param("telphoneID", "13951744261"));
            // 注册
//            MvcResult re = mockMvc.perform((post("/register").param("telphoneID", "13951744261")
//                   .param("verifyCode", "1111")
//                   .param("password", "123456")
//                   .param("userType", String.valueOf(RoleType.MODEL))))
//                   .andReturn();
//            
//            String resStr = re.getResponse().getContentAsString();
//            System.out.println(resStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @After
    public void checkCache()
    {
    }
}
