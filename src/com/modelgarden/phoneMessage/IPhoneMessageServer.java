package com.modelgarden.phoneMessage;

/**
 * 手机短信服务接口
 * 不同的服务商平台各自实现服务类
 * @author Administrator
 *
 */
public interface IPhoneMessageServer
{
    /**
     * 发送短信验证码
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    public boolean sendVerifyCode(String phoneNumber, String verifyCode);
}
