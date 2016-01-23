package com.modelgarden.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import com.modelgarden.common.ResultMessage;
import com.modelgarden.model.BaseUser;
import com.modelgarden.model.ModelInfo;

public class CommonUitl
{
    public static final String ROLE_BASICUSER = "baseUser";
    
    public static final String ROLE_CEREMONYMODEL = "ceremonyModel";
    
    public static final String ROLE_PHOTOGRAPHER = "photographer";
    
    public static final String ROLE_TAOMODEL = "taoModel";
    
    public static final String ROLE_SYSADMIN = "sysAdmin";
    
    /**
     * 生成一个唯一的ID
     * 
     * @return
     */
    public static String generateId()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
    
    /**
     * 生成一个随机的6位数
     * @return
     */
    public static String sixNumberRadom()
    {
        String charValue = "";
        for (int i = 0; i < 6; i++)
        {
            char c = (char)(randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
    
    private static int randomInt(int from, int to)
    {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
    
    /**
     * 根据字符串生成MD5码
     * 
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr)
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
        {
            byteArray[i] = (byte)charArray[i];
        }
        
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++)
        {
            int val = ((int)md5Bytes[i]) & 0xff;
            if (val < 16)
            {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
    
    /**
     * 根据当前的主机时间重命名文件
     * 
     * @param name
     * @return
     */
    public static String renameFile(String fileName)
    {
        if (fileName == null || fileName.isEmpty())
        {
            return "";
        }
        String prefix = String.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        // 得到文件的扩展名，无扩展名则得到全名
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return prefix + "." + ext;
    }
    
    /**
     * 上传图片
     * @param file
     * @param user
     */
    public static void uploadImage(MultipartFile file, BaseUser user)
    {
        String fileName = CommonUitl.renameFile(file.getOriginalFilename());
        try
        {
            InputStream is = file.getInputStream();
            MGFTPClient ftpClient = new MGFTPClient("f5c2cfeb6c690d33839ec712c33eb955");
            ftpClient.connect();
            ftpClient.uploadFile(fileName, is);
            ftpClient.disConnnect();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
       
    }

    public static void main(String[] args) throws IOException
    {
        String id = generateId();
        System.out.println(id);
        
        String str = "zhangfengaiwuyan";
        String md5 = string2MD5(str);
        System.out.println(md5);
    }
}
