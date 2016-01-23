package com.modelgarden.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modelgarden.common.MessageContants;
import com.modelgarden.common.ResultMessage;
import com.modelgarden.common.RoleType;
import com.modelgarden.model.BaseUser;
import com.modelgarden.phoneMessage.IPhoneMessageServer;
import com.modelgarden.service.IUserService;
import com.modelgarden.util.CacheUtil;
import com.modelgarden.util.CommonUitl;
import com.modelgarden.util.UserInfoCenter;

@Controller
public class BaseUserController
{
    private static Logger logger = Logger.getLogger(BaseUserController.class);
    
	@Autowired
	@Qualifier("userService")
    private IUserService userService;
	
	@Autowired
    @Qualifier("phoneMessageServer")
	private IPhoneMessageServer phoneMessageServer;
	
	@Autowired
	private UserInfoCenter userInfoCenter;
	
	@Autowired
	private CacheUtil cacheUtil;
	
	
	@RequestMapping(value="/getVerifyCode",method = RequestMethod.POST)
	@ResponseBody
	public ResultMessage getVerifyCode(@RequestParam("telphoneID") String telID)
	{
	    ResultMessage msg = new ResultMessage();
	    msg.setResult(ResultMessage.RESULT_SUCCEED);
	    
	    // 生成验证码
	    String verifyCode = userService.genarateVerifyCdoe(telID);

	    // 发送验证码
	    if(!phoneMessageServer.sendVerifyCode(telID, verifyCode))
	    {
	        msg.setResult(ResultMessage.RESULT_FAILED);
	        msg.setError(MessageContants.FAILED_TO_SEND_VERIFYCODE);
	    }
	    
	    return msg;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegister()
	{
	    return "home/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ResultMessage register(String telphoneID,
	                              String verifyCode,
	                              String password,
	                              RoleType userType) 
	{
	    ResultMessage msg = new ResultMessage();
	    
	    // 先查找是否存在
		BaseUser user = userService.getBaseUser(telphoneID);
		if(user != null)
		{
		    msg.setResult(ResultMessage.RESULT_FAILED);
		    msg.setError(MessageContants.USER_EXISTS);
		    logger.error(MessageContants.USER_EXISTS);
		    return msg;
		}
		
		// 获取缓存的验证码
		String cacheCode = userService.getVerifyCdoe(telphoneID);
		if(cacheCode == null || !cacheCode.equals(verifyCode))
		{
		    msg.setResult(ResultMessage.RESULT_FAILED);
            msg.setError(MessageContants.ERROR_VERIFYCODE);
            logger.error(MessageContants.ERROR_VERIFYCODE);
            return msg;
		}
	
		// 添加用户
		BaseUser baseUsr = new BaseUser(telphoneID, 
		           CommonUitl.string2MD5(password), RoleType.MODEL);
        if(!userService.addBaseUser(baseUsr))
        {
            msg.setResult(ResultMessage.RESULT_FAILED);
            msg.setError(MessageContants.FAILED_TO_ADD_USER);
            logger.error(MessageContants.FAILED_TO_ADD_USER);
        }
       
		msg.setResult(ResultMessage.RESULT_SUCCEED);
		return msg;
	}

	
	/**
	 * 登录请求
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public ResultMessage login(@RequestParam("username")String username, 
	                           @RequestParam("password")String password) 
    {
	    ResultMessage resMsg = new ResultMessage();
	    Subject currentUser = SecurityUtils.getSubject(); 
        if (!currentUser.isAuthenticated()) 
        {
            UsernamePasswordToken token = new UsernamePasswordToken(username,
                                                                    password); 
            try 
            {  
                currentUser.login(token);  
            } 
            catch (UnknownAccountException uae) 
            {  
                resMsg.setResult(ResultMessage.RESULT_FAILED);
                resMsg.setError(MessageContants.USER_OR_PASSWORD_WRONG.toString());
                logger.info(MessageContants.USER_OR_PASSWORD_WRONG.toString());  
                return resMsg;  
            } 
            catch (IncorrectCredentialsException ice) 
            {  
                resMsg.setResult(ResultMessage.RESULT_FAILED);
                resMsg.setError(MessageContants.USER_OR_PASSWORD_WRONG);
                logger.info(MessageContants.USER_OR_PASSWORD_WRONG);  
                return resMsg;  
            } 
            catch (LockedAccountException lae) 
            {  
                resMsg.setResult(ResultMessage.RESULT_FAILED);
                logger.info("用户被锁定： " + token.getPrincipal());  
                return resMsg;  
            } 
            catch(Exception e)
            {
                resMsg.setResult(ResultMessage.RESULT_FAILED);
                logger.error(e.getMessage(), e);
                return resMsg;
            }
        }
        
        resMsg.setResult(ResultMessage.RESULT_SUCCEED);
        return resMsg;
    }
	
	/**
	 * 登出请求
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public ResultMessage logout()
	{
	    Subject curUser = SecurityUtils.getSubject();
	    if(curUser.isAuthenticated())
	    {
	        curUser.logout();
	    }
	    
	    ResultMessage resMsg = new ResultMessage();
	    resMsg.setResult(ResultMessage.RESULT_SUCCEED);
	    return resMsg;
	}
}
