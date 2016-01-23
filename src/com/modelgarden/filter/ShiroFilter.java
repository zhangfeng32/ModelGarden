package com.modelgarden.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

public class ShiroFilter extends FormAuthenticationFilter
{
    @Override  
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        super.postHandle(request, response);
    }
}
