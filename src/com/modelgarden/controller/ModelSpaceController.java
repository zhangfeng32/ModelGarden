package com.modelgarden.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.modelgarden.common.CommonConstants;
import com.modelgarden.common.ImgType;
import com.modelgarden.common.MessageContants;
import com.modelgarden.common.ResultMessage;
import com.modelgarden.model.BaseUser;
import com.modelgarden.model.ImageInfo;
import com.modelgarden.model.ModelInfo;
import com.modelgarden.service.IModelService;
import com.modelgarden.service.IUserService;
import com.modelgarden.util.CommonUitl;
import com.modelgarden.util.UserInfoCenter;

@Controller
public class ModelSpaceController
{
    @Autowired
    private IUserService userService;
    
    @Autowired
    private IModelService modelService;
    
    @Autowired
    private UserInfoCenter userInfoCenter;
    
    private static Logger logger = Logger.getLogger(ModelSpaceController.class);
    
    /**
     * 获取模特信息消息
     * @author www.0001.ga
     */
    private class GetModelInfoMsg
    {
        public Object modelInfo;
    }
    
    @RequestMapping(value = "/getModelDetailInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage getModelDetailInfo(@RequestParam("modelID") String modelID)
    {
        ResultMessage msg = new ResultMessage();
        msg.setResult(ResultMessage.RESULT_SUCCEED);
        
        ModelInfo model = modelService.getModelInfo(modelID);
        if(model == null)
        {
            msg.setResult(ResultMessage.RESULT_FAILED);
            msg.setError(MessageContants.USER_NOT_LOGIN);
            logger.error(MessageContants.USER_NOT_LOGIN);
            return msg;
        }
     
        GetModelInfoMsg resMsg = new GetModelInfoMsg();
        resMsg.modelInfo = model;
        
        msg.setMessage(resMsg);
        return msg;
    }
    
    /**
     * 获取模特照片消息
     * 
     */
    private class GetModelImagesMsg
    {
        public Object imgeList;
    }
    
    @RequestMapping(value = "/getModelImages", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage getModelImages(@RequestParam("modelID") String modelID,
                                        @RequestParam("imgType")ImgType imgType)
    {
        ResultMessage msg = new ResultMessage();
        msg.setResult(ResultMessage.RESULT_SUCCEED);
        
        List<ImageInfo> imgLst = userService.getImages(modelID, imgType);
        if(imgLst == null)
        {
            msg.setResult(ResultMessage.RESULT_FAILED);
            msg.setError(MessageContants.FAILED_TO_GET_MODEL_IAMGES);
            logger.error(MessageContants.FAILED_TO_GET_MODEL_IAMGES);
            return msg;
        }
        
        GetModelImagesMsg imgMsg = new GetModelImagesMsg();
        imgMsg.imgeList = imgLst.toArray();
        
        msg.setMessage(imgMsg);
        return msg;
    }
    
    @RequestMapping(value = "/updateModelInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultMessage updateModelInfo(ModelInfo model)
    {
        ResultMessage msg = new ResultMessage();
        msg.setResult(ResultMessage.RESULT_SUCCEED);
        
        BaseUser user = userInfoCenter.getCurrentUserInfo();
        // 获取用户信息，测试注释
//        if(user == null)
//        {
//            msg.setResult(ResultMessage.RESULT_FAILED);
//            msg.setError(MessageContants.USER_NOT_LOGIN);
//            logger.error(MessageContants.USER_NOT_LOGIN);
//            return msg;
//        }
        
        model.setUid(user.getId());
        if(!modelService.updateModelInfo(model))
        {
            msg.setResult(ResultMessage.RESULT_FAILED);
            msg.setError(MessageContants.FAILED_TO_UPDATE_MODEL_INFO);
            logger.error(MessageContants.FAILED_TO_UPDATE_MODEL_INFO);
        }
        
        return msg;
    }
    

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public Object uploadImage(
            @RequestParam(value="imagefile", required=false) MultipartFile imagefile,
            HttpServletRequest request, ModelMap model)
    {
        ResultMessage msg = new ResultMessage();
        msg.setResult(ResultMessage.RESULT_SUCCEED);
        
        //获取当前用户信息
        BaseUser user = userInfoCenter.getCurrentUserInfo();
        // 获取用户信息，测试注释
//        if(user == null)
//        {
//            msg.setResult(ResultMessage.RESULT_FAILED);
//            msg.setError(MessageContants.USER_NOT_LOGIN);
//            logger.error(MessageContants.USER_NOT_LOGIN);
//            return msg;
//        }
       
        try
        {
            // 保存至用户空间下面
//            String uid = user.getId();
            String uid = "13951744261";
            String path = request.getSession().getServletContext().getRealPath(CommonConstants.IMAGE_REAL_PATH); 
            String fileName = CommonUitl.renameFile(imagefile.getOriginalFilename());
            
            File uidFile = new File(path, uid);  
            if (!uidFile.exists())
            {
                uidFile.mkdirs();
            }

            File targetFile = new File(uidFile, fileName);
            if (!targetFile.exists())
            {
                targetFile.mkdirs();
            }
            
            // 保存
            imagefile.transferTo(targetFile);
            
            String uri = new StringBuffer(CommonConstants.IMAGE_URL_PATH)
                .append("/")
                .append(uid)
                .append("/")
                .append(fileName).toString();
            
            // 路径保存到数据中
            if(!userService.saveImage(uid, uri, ImgType.PHOTO))
            {
                msg.setResult(ResultMessage.RESULT_FAILED);
                msg.setError(MessageContants.FAILED_TO_UPDATE_IMAGE_DATA);
                logger.error(MessageContants.FAILED_TO_UPDATE_IMAGE_DATA);
                return msg;
            }
            
            msg.setMessage(uri);
        }
        catch (Exception e)
        {
            msg.setResult(ResultMessage.RESULT_FAILED);
            msg.setError(MessageContants.FAILED_TO_UPLOAD_IMAGE);
            logger.error(e.getMessage(), e);
        }
        
        return msg;
    }
    
    
}
