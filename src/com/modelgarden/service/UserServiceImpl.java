package com.modelgarden.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.modelgarden.common.ImgType;
import com.modelgarden.dao.IUserDao;
import com.modelgarden.model.BaseUser;
import com.modelgarden.model.ImageInfo;
import com.modelgarden.util.CacheUtil;
import com.modelgarden.util.CommonUitl;

@Service("userService")
public class UserServiceImpl implements IUserService
{
    @Autowired
    @Qualifier("userDao")
    private IUserDao userDao;
    
    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public BaseUser getBaseUser(String userName)
    {
        // 先从缓存里面获取
        BaseUser user = cacheUtil.getObjectUserCache(userName, CacheUtil.BASEINFO_KEY, BaseUser.class);
        if(user != null)
        {
            return user;
        }
        
        return userDao.getBaseUser(userName);
    }
    
    @Override
    public String genarateVerifyCdoe(String phoneID)
    {
        String sixIntRandom = CommonUitl.sixNumberRadom();
 
        // 加入缓存
        cacheUtil.setUserCache(phoneID, CacheUtil.VERIFYCODE_KEY, sixIntRandom);
        
        return sixIntRandom;
    }
    
    @Override
    public String getVerifyCdoe(String phoneID)
    {
        // 获取缓存验证码
        return  cacheUtil.getUserCache(phoneID, CacheUtil.VERIFYCODE_KEY);
    }

    @Override
    public boolean addBaseUser(BaseUser baseUsr)
    {
        // 先更新缓存，没有的话新增
        if(baseUsr != null)
        {
            cacheUtil.setObjectUserCache(baseUsr.getId(), CacheUtil.BASEINFO_KEY, baseUsr);
        }
        
         return userDao.addBaseUser(baseUsr.getId(), baseUsr.getPassword(), baseUsr.getRoleType());
    }

    @Override
    public boolean saveImage(String uid, String uriPath, ImgType imgType)
    {
        return userDao.saveImage(uid, uriPath, imgType);
    }

    @Override
    public List<ImageInfo> getImages(String uid, ImgType imgType)
    {
        return userDao.getImages(uid, imgType);
    }
    
}
