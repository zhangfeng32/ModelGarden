package com.modelgarden.filter.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.modelgarden.model.BaseUser;
import com.modelgarden.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ShiroDbRealm extends AuthorizingRealm
{
    @Autowired
    @Qualifier("userService")
    private IUserService userService;
    
    /**
     *  认证回调函数。
     *  login登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
        throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken; 
        String telID = token.getUsername();
        
        BaseUser user = userService.getBaseUser(telID);
        if(user != null)
        {  
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getId(), 
                                user.getPassword(), this.getName());
            return authcInfo;  
        }
        return null;
    }
    
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        return null;
    }
    
    
    
}
