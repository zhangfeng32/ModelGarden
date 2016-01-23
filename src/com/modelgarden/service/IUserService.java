package com.modelgarden.service;


import java.util.List;

import com.modelgarden.common.ImgType;
import com.modelgarden.common.RoleType;
import com.modelgarden.model.BaseUser;
import com.modelgarden.model.ImageInfo;

public interface IUserService
{
    /**
     * 生成验证码，并且写入缓存
     * @param phoneID
     * @return
     */
    public String genarateVerifyCdoe(String phoneID);
    
    /**
     * 获取缓存的验证码
     * @param phoneID
     * @return
     */
    public String getVerifyCdoe(String phoneID);
    
    /**
     * 获取基本用户信息
     * @param telID
     * @return
     */
    public BaseUser getBaseUser(String telID);
    
    /**添加基本用户
     * @param telID
     * @param password
     * @param userType
     * @throws Exception
     */
    public boolean addBaseUser(BaseUser baseUser);
    
    /**
     * 保存图片
     * @param uid
     * @param uriPath
     * @param imgType
     * @return
     */
    public boolean saveImage(String uid, String uriPath, ImgType imgType);
    
    /**
     * 获取图片信息
     * @param uid
     * @return
     */
    public List<ImageInfo> getImages(String uid, ImgType imgType);
    
}
