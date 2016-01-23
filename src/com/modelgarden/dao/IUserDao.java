package com.modelgarden.dao;

import java.util.List;

import com.modelgarden.common.ImgType;
import com.modelgarden.common.RoleType;
import com.modelgarden.model.BaseUser;
import com.modelgarden.model.ImageInfo;

public interface IUserDao
{
	public BaseUser getBaseUser(String telID);
	
	public boolean addBaseUser(String telID, String password, RoleType userType);
	
	public boolean saveImage(String uid, String uriPath, ImgType imgType);
	
	public List<ImageInfo> getImages(String uid, ImgType imgType);
}
