package com.modelgarden.util;


import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository("jsonUtil")
public class JsonUtil
{
    private static Logger logger = Logger.getLogger(JsonUtil.class);
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 把JavaBean转换为json字符串
     * 
     * @param object
     * @return
     */
    public String toJSon(Object object)
    {
        try
        {
            return objectMapper.writeValueAsString(object);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。 (1)转换为普通JavaBean：readValue(json,Student.class) (2)转换为List,如List
     * <Student>,将第二个参数传递为Student [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     * 
     * @param jsonStr
     * @param valueType
     * @return
     */
    public  <T> T readObject(String jsonStr, Class<T> valueType)
    {
        try
        {
            return objectMapper.readValue(jsonStr, valueType);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
    public <T> List<T> readArrayList(String jsonStr, Class<T> valueType)
    {
        try
        {
            return objectMapper.readValue(jsonStr, 
                                objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        
        return null;
    }
    
}
