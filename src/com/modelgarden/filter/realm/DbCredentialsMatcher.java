package com.modelgarden.filter.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.modelgarden.util.CommonUitl;

public class DbCredentialsMatcher extends SimpleCredentialsMatcher
{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) 
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        if(token == null || token.getPassword() == null)
        {
            return false;
        }
        
        Object tokenCredentials = String.valueOf(token.getPassword());
        Object accountCredentials = getCredentials(info);
        
        return equals(tokenCredentials, accountCredentials);
    }
    
    
}
