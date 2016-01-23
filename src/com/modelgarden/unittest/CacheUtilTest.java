package com.modelgarden.unittest;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.modelgarden.common.ImgType;
import com.modelgarden.model.ImageInfo;
import com.modelgarden.model.ModelInfo;
import com.modelgarden.util.CacheUtil;
import com.modelgarden.util.JsonUtil;

public class CacheUtilTest extends AbstractTestNGTest
{
    @Autowired
    private CacheUtil cacheUtil;
    
    @Test
    public void objectCacheTest()
    {
        try
        {
            
            ModelInfo model = new ModelInfo();
            model.setId("13951744261");
            model.setAge(19);
            model.setAddress("kkk");
            
            cacheUtil.setObject("13951744261", model);
            
            ModelInfo modelTemp = cacheUtil.getObject("13951744261", ModelInfo.class);
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void arrayCacheTest()
    {
        try
        {
            
            List<ImageInfo> lst = new ArrayList<ImageInfo>();
            ImageInfo imgInfo = new ImageInfo();
            imgInfo.setImgType(ImgType.HEAD_IMAGE);
            imgInfo.setUid("13951744261");
            imgInfo.setUri("ddd");
            
            ImageInfo imgInfo2 = new ImageInfo();
            imgInfo2.setImgType(ImgType.HEAD_IMAGE);
            imgInfo2.setUid("111111");
            imgInfo2.setUri("ccccc");
            
            lst.add(imgInfo);
            lst.add(imgInfo2);
 
            
            cacheUtil.setObject("13951744261", lst);
            
            List<ImageInfo> lst2 = cacheUtil.getArrayList("13951744261", ImageInfo.class);
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
