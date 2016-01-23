package com.modelgarden.dao;

import com.modelgarden.model.ModelInfo;

public interface IModelDao
{
    public boolean updateModelInfo(ModelInfo model);
    
    public ModelInfo getModelInfo(String modelID);
}
