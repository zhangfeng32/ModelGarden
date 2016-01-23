package com.modelgarden.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.modelgarden.common.ImgType;
import com.modelgarden.dao.IModelDao;
import com.modelgarden.dao.IUserDao;
import com.modelgarden.model.ImageInfo;
import com.modelgarden.model.ModelInfo;
import com.modelgarden.util.CacheUtil;

/**
 * @author www.0001.ga
 *
 */
@Service("modelService")
public class ModelServiceImpl implements IModelService
{
    @Autowired
    @Qualifier("modelDao")
    private IModelDao modelDao;
    
    @Autowired
    private CacheUtil cacheUtil;

    @Override
    public boolean updateModelInfo(ModelInfo model)
    {    
        // 添加缓存
        if(model != null)
        {
            cacheUtil.setObjectUserCache(model.getId(), CacheUtil.MODELINFO_KEY, model);
        }
        
        return modelDao.updateModelInfo(model);
    }

    @Override
    public ModelInfo getModelInfo(String modelID)
    {
        // 先从缓存获取
        ModelInfo model = cacheUtil.getObjectUserCache(modelID, 
                        CacheUtil.MODELINFO_KEY, ModelInfo.class);
        if(model != null)
        {
            return model;
        }
        
        return modelDao.getModelInfo(modelID);
    }
    
}
