package com.modelgarden.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modelgarden.common.ImgType;
import com.modelgarden.common.RoleType;
import com.modelgarden.model.BaseUser;
import com.modelgarden.model.ImageInfo;


@Repository("userDao")
public class UserDaoImpl implements IUserDao
{
    
    @Autowired
    private SqlSessionFactory modelDBSessionFactory;
    
    public UserDaoImpl()
    {
    }
    
    private static Logger logger = Logger.getLogger(UserDaoImpl.class);
    
    /* 
     * 根据ID查询
     */
    public BaseUser getBaseUser(String telID)
    {
        SqlSession sqlSession = null;
        BaseUser user = null;
        try
        {
            sqlSession = modelDBSessionFactory.openSession();
            user = sqlSession.selectOne("com.modelgarden.model.mapper.user.selectUserByTelID", telID);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            sqlSession.close();
        }
        
        return user;
    }
    
    /* 
     * 
     */
    @Override
    public boolean addBaseUser(String telID, String password, RoleType userType)
    {
        SqlSession sqlSession = null;
        try
        {
            sqlSession = modelDBSessionFactory.openSession();
            BaseUser user = new BaseUser();
            user.setId(telID);
            user.setPassword(password);
            user.setTelephone(telID);
            user.setRoleType(userType);
            sqlSession.insert("com.modelgarden.model.mapper.user.addBaseUser", user);
            sqlSession.commit();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return false;
        }
        finally
        {
            sqlSession.close();
        }
        
        return true;
    }

    @Override
    public boolean saveImage(String uid, String uriPath, ImgType imgType)
    {
        SqlSession sqlSession = null;
        try
        {
            Map map = new HashMap<String, String>();
            map.put("uid", uid);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(new Date());
            map.put("createTime", dateStr);
            map.put("type", imgType);
            map.put("location", uriPath);
            
            sqlSession = modelDBSessionFactory.openSession();
          
            sqlSession.insert("com.modelgarden.model.mapper.user.saveImage", map);
            sqlSession.commit();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return false;
        }
        finally
        {
            sqlSession.close();
        }
        
        return true;
    }

    @Override
    public List<ImageInfo> getImages(String uid, ImgType imgType)
    {
        SqlSession sqlSession = null;
        BaseUser user = null;
        List<ImageInfo> imgLst = null;
        try
        {
            Map map = new HashMap<String, String>();
            map.put("userID", uid);
            map.put("imgType", imgType);
            sqlSession = modelDBSessionFactory.openSession();
            imgLst = sqlSession.selectList("com.modelgarden.model.mapper.user.selectImagesByID", map);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            sqlSession.close();
        }
        
        return imgLst;
    }

}
