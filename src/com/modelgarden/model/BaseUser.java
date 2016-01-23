package com.modelgarden.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.modelgarden.common.RoleType;

/**
 * @author Administrator
 *
 */
public class BaseUser
{
    private String id;
    private String registerTime;
    private String password;
    private String telephone;
    private RoleType roleType;
    
    public BaseUser()
    {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        registerTime = sdf.format(new Date());
    }
    
    public BaseUser(String uid, String passwd, RoleType roleType)
    {
        this.id = uid;
        this.password = passwd;
        this.roleType = roleType;
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.registerTime = sdf.format(new Date());
    }
    
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getRegisterTime()
    {
        return registerTime;
    }
    public void setRegisterTime(String registerTime)
    {
        this.registerTime = registerTime;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getTelephone()
    {
        return telephone;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }
    public RoleType getRoleType()
    {
        return roleType;
    }
    public void setRoleType(RoleType roleType)
    {
        this.roleType = roleType;
    }
    
    
}
