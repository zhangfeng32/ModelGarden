package com.modelgarden.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class MGFTPClient
{
    private String serverIP;
    
    private int serverPort;
    
    private String username;
    
    private String password;
    
    // 默认的用户目录
    private String filePath;
    
    private FTPClient ftpClient;
    
    private Logger logger = Logger.getLogger(MGFTPClient.class);
    
    public MGFTPClient(String filePath)
    {
        this.filePath = filePath;
        initConfig();
    }
    
    /**
     * 初始化FTP配置
     */
    private void initConfig()
    {
        InputStream is = null;
        try
        {
            Properties properties = new Properties();
            is = MGFTPClient.class.getResourceAsStream("/ftp.properties");
            properties.load(is);
            serverIP = (String)properties.get("modelgarden.ftpServer");
            String strServerPort = (String)properties.get("modelgarden.ftpPort");
            serverPort = Integer.parseInt(strServerPort);
            username = (String)properties.get("modelgarden.username");
            password = (String)properties.get("modelgarden.password");
            
            // 实例化FTP客户端，并将目录设置为默认目录
            ftpClient = new FTPClient();
        }
        catch(Exception e)
        {
            logger.error(e);
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
                    logger.error(e);
                }
            }
        }
        
    }
    
    /**
     * 连接ftp服务
     * @return
     */
    public boolean connect()
    {
        try
        {
            ftpClient.connect(serverIP,serverPort);
            ftpClient.login(username, password);
            
            int reply = ftpClient.getReplyCode();
            if (FTPReply.isProtectedReplyCode(reply))
            {
                logger.error("Fail to connect to FTP Server: " + reply);
                ftpClient.disconnect();
                return false;
            }
            
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");
            boolean result = ftpClient.changeWorkingDirectory("/modelgarden");
        }
        catch (Exception e)
        {
            disConnnect();
            logger.error(e);
            return false;
        }
        
        return true;
    }
    
    /**
     * 断开FTP服务
     */
    public void disConnnect()
    {
        try
        {
            if (ftpClient != null)
            {
                ftpClient.logout();
            }
        }
        catch(Exception e)
        {
            logger.error(e);
        }
        finally
        {
            if (ftpClient.isConnected())
            {
                try
                {
                    ftpClient.disconnect();
                }
                catch (IOException e)
                {
                    logger.error(e);
                }
            }
        }
    }
    
    public boolean uploadFile(String fileName, InputStream in)
    {
        try
        {
            ftpClient.storeFile(fileName, in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args)
    {
        InputStream is = null;
        try
        {
            is = new FileInputStream(new File("F:\\f5c2cfeb6c690d33839ec712c33eb955.jpg"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        MGFTPClient ftpManager = new MGFTPClient("/modelgarden");
        if (! ftpManager.connect())
        {
            return;
        }
        ftpManager.uploadFile("hello.jpg", is);
        ftpManager.disConnnect();
    }
}
