package com.modelgarden.model;

import com.modelgarden.common.ImgType;

public class ImageInfo
{
    private String uid;
    private String uri;
    private String createTime;
    private ImgType imgType;
    
    public String getUid()
    {
        return uid;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }
    public String getUri()
    {
        return uri;
    }
    public void setUri(String uri)
    {
        this.uri = uri;
    }
    public String getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }
    public ImgType getImgType()
    {
        return imgType;
    }
    public void setImgType(ImgType imgType)
    {
        this.imgType = imgType;
    }
    
    
}
