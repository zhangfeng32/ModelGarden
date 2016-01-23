package com.modelgarden.util;

import java.util.List;

import org.apache.ibatis.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modelgarden.model.ModelInfo;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Repository("cacheUtil")
public class CacheUtil
{
    public static final String VERIFYCODE_KEY = "verifyCode";
    public static final String BASEINFO_KEY = "baseInfo";
    public static final String MODELINFO_KEY = "modelInfo";
    
    
    @Autowired
    private JsonUtil jsonUtil;
    
    @Autowired
    private ShardedJedisPool shardedJedisPool;
    
    
    
    /**
     * 设置某个用户的缓存 value为字符串类型
     * @param key
     * @param value
     */
    public void setUserCache(String uid, String orginKey, String value)
    {
        String key = this.genarateUserKey(uid, orginKey);
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.set(key, value);
    }
    
    /**
     * 获取某个用户的缓存 字符串类型的value
     * @param key
     * @return
     */
    public String getUserCache(String uid, String orginKey)
    {
        String key = this.genarateUserKey(uid, orginKey);
        ShardedJedis jedis = shardedJedisPool.getResource();
        return jedis.get(key);
    }
    
    /**
     * 设置某个用户的缓存 value为 类、 List 类型
     * @param key
     * @param value
     */
    public void setObjectUserCache(String uid, String orginKey, Object value)
    {
        String key = this.genarateUserKey(uid, orginKey);
        String jsonValue = jsonUtil.toJSon(value);
        this.set(key, jsonValue);
    }
    
    /**
     * 获取某个用户的缓存  类 类型的value
     * @param key
     * @return
     */
    public <T> T getObjectUserCache(String uid, String orginKey, Class<T> valueType)
    {
        String key = this.genarateUserKey(uid, orginKey);
        String jsonValue = this.get(key);
        return jsonUtil.readObject(jsonValue, valueType);
    }
    
    /**
     * 获取某个用户的缓存  List 类型的 value
     * @param key
     * @param value
     */
    public <T> List<T> getArrayListUserCache(String uid, String orginKey, Class<T> valueType)
    {
        String key = this.genarateUserKey(uid, orginKey);
        String jsonValue = this.get(key);
        return jsonUtil.readArrayList(jsonValue, valueType);
    }
    
    
    /**
     * 删除某个用户的缓存
     * @param key
     */
    public void deleteUserCache(String uid, String orginKey)
    {
        String key = this.genarateUserKey(uid, orginKey);
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.del(key);
    }
    
    
    /**
     * 生成属于某个用户的key
     * @param uid
     * @param orginKey
     * @return
     */
    public String genarateUserKey(String uid, String orginKey)
    {
        return uid + "|" + orginKey;
    }
    
    /**
     * 设置value为字符串类型
     * @param key
     * @param value
     */
    public void set(String key, String value)
    {
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.set(key, value);
    }
    
    /**
     * 获取字符串类型的value
     * @param key
     * @return
     */
    public String get(String key)
    {
        ShardedJedis jedis = shardedJedisPool.getResource();
        return jedis.get(key);
    }
    
    /**
     * 设置value为 类、 List 类型
     * @param key
     * @param value
     */
    public void setObject(String key, Object value)
    {
        String jsonValue = jsonUtil.toJSon(value);
        this.set(key, jsonValue);
    }
    
    /**
     * 获取 类 类型的value
     * @param key
     * @return
     */
    public <T> T getObject(String key, Class<T> valueType)
    {
        String jsonValue = this.get(key);
        return jsonUtil.readObject(jsonValue, valueType);
    }
    
    /**
     * 获取 List 类型的 value
     * @param key
     * @param value
     */
    public <T> List<T> getArrayList(String key, Class<T> valueType)
    {
        String jsonValue = this.get(key);
        return jsonUtil.readArrayList(jsonValue, valueType);
    }
    
    /**
     * 删除
     * @param key
     */
    public void delete(String key)
    {
        ShardedJedis jedis = shardedJedisPool.getResource();
        jedis.del(key);
    }
    
    
    
}
