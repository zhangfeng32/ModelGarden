package com.modelgarden.phoneMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 集成阿里大鱼短信平台服务
 * @author Administrator
 *
 */
@Service("phoneMessageServer")
public class TaobaoPhoneMessageServer implements IPhoneMessageServer
{
    private static Logger logger = Logger.getLogger(TaobaoPhoneMessageServer.class);

    private String serverUrl;
    private String appKey;
    private String appSecret;
    private String verifyCodeTemplate;
    private String signName;
    
    private HttpClient httpClient;
    
    private static TaobaoPhoneMessageServer Instance;
    
    private static Object sysObject = new Object();
    
    private TaobaoPhoneMessageServer()
    {
        initConfig();
        if (httpClient == null)
        {
            httpClient = new DefaultHttpClient();
        }
    }
    
    public static TaobaoPhoneMessageServer getInstance()
    {
        if (Instance == null)
        {
            synchronized (sysObject)
            {
                try
                {
                    if (Instance == null)
                    {
                        Instance = new TaobaoPhoneMessageServer();
                    }
                }
                catch (Exception e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
        }

        return Instance;
    }
    
    /**
     * 初始化短信推送平台
     */
    private void initConfig()
    {
        InputStream is = null;
        try
        {
            Properties properties = new Properties();
            is = TaobaoPhoneMessageServer.class.getResourceAsStream("/phonemessage.properties");
            properties.load(is);
            serverUrl = (String)properties.get("modelgarden.verifycode.serverUrl");
            appKey = (String)properties.get("modelgarden.verifycode.appKey");
            appSecret = (String)properties.get("modelgarden.verifycode.appSecret");
            verifyCodeTemplate = (String)properties.get("modelgarden.verifycode.verifyCodeTemplate");
            signName = (String)properties.get("modelgarden.verifycode.signName");
        }
        catch(Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
    
    @Override
    public boolean sendVerifyCode(String phoneNumber, String verifyCode)
    {
        TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName(signName);
        req.setSmsParamString("{\"verifycode\":\"" + verifyCode+ "\"}");
        req.setRecNum(phoneNumber);
        req.setSmsTemplateCode(verifyCodeTemplate);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try
        {
            rsp = client.execute(req);
        }
        catch (ApiException e)
        {
            e.printStackTrace();
            logger.error(e);
            return false;
        }
        BizResult result = rsp.getResult();
        if (result == null)
        {
            logger.error(rsp.getBody());
            return false;
        }
        
        if (!result.getSuccess())
        {
            logger.error(rsp.getBody());
            return false;
        }
        
        return true;
    }
    
    
    public static void main(String[] args)
    {
        TaobaoPhoneMessageServer.getInstance().sendVerifyCode("13675197013", "568413");
    }
    
}
