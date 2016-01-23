package com.modelgarden.util;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.modelgarden.model.BaseUser;
import com.modelgarden.service.IUserService;

@Service("userInfoCenter")
public class UserInfoCenter
{
    private static Logger logger = Logger.getLogger(UserInfoCenter.class);
    
    @Autowired
    @Qualifier("userService")
    private IUserService userService;
   
    
    /**
     * 获取当前用户基本信息
     * 后续需要加入到缓存系统中
     * @return
     */
    public BaseUser getCurrentUserInfo()
    {
        try
        {
            Subject curUser = SecurityUtils.getSubject();
            String curUserName = (String) curUser.getPrincipal();
            if(curUserName == null || curUserName.isEmpty())
            {
                return null;
            }
            
            BaseUser user = userService.getBaseUser(curUserName); 
            return user;
        }
        catch(Exception e)
        {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
