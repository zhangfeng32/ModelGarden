package com.modelgarden.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modelgarden.model.BaseUser;
import com.modelgarden.model.ModelInfo;

@Repository("modelDao")
public class ModelDaoImpl implements IModelDao
{
    @Autowired
    private SqlSessionFactory modelDBSessionFactory;
    
    private static Logger logger = Logger.getLogger(ModelDaoImpl.class);
    
    @Override
    public boolean updateModelInfo(ModelInfo model)
    {
        SqlSession sqlSession = null;
        try
        {
            sqlSession = modelDBSessionFactory.openSession();
            sqlSession.insert("com.modelgarden.model.mapper.model.updateModelInfo", model);
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
    public ModelInfo getModelInfo(String modelID)
    {
        ModelInfo model = null;
        SqlSession sqlSession = null;
        try
        {
            sqlSession = modelDBSessionFactory.openSession();
            model = sqlSession.selectOne("com.modelgarden.model.mapper.model.selectModelByID", modelID);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return null;
        }
        finally
        {
            sqlSession.close();
        }
        
        return model;
    }
    
    
}
