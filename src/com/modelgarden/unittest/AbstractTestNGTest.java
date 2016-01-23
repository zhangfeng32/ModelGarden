package com.modelgarden.unittest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;  
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.context.WebApplicationContext;  

/**
 * @author zhangfeng
 * 单元测试基类，所有单元测试类均要继承此类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
    "file:WebContent/WEB-INF/modelgarden-servlet.xml",
    "file:WebContent/WEB-INF/applicationContext.xml"})
public class AbstractTestNGTest
{
    @Autowired
    protected WebApplicationContext wac;
    
    protected MockMvc mockMvc;
    
    @Before
    public void setup() throws Exception 
    {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }
    
}
