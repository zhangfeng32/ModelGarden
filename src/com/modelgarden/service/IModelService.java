package com.modelgarden.service;

import java.util.List;

import com.modelgarden.common.ImgType;
import com.modelgarden.model.ImageInfo;
import com.modelgarden.model.ModelInfo;

public interface IModelService
{
    /**
     * 完善模特信息，有则更新，没有新建
     * @param phoneID
     * @return
     */
    public boolean updateModelInfo(ModelInfo model);

    /**
     * 获取模特详情
     * @param modelID
     * @return
     */
    public ModelInfo getModelInfo(String modelID);
    
  
}
