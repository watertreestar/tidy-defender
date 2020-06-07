package com.ranger.defender.cache.redis;

import com.ranger.defender.cache.Cache;
import com.ranger.defender.util.JSONUtil;
import com.ranger.defender.util.SpringContextUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author ranger
 * @Date 2020/6/7 12:14
 **/
public class RedisCache<V> implements Cache<String,V> {

    private RedisTemplate<String,String> redisTemplate;

    private String prefix;

    public RedisCache(RedisTemplate redisTemplate,String prefix){
        this.redisTemplate = redisTemplate;
        this.prefix = prefix;
    }

    public RedisCache(){
        this( SpringContextUtil.getBean(RedisTemplate.class),"");
    }

    @Override
    public void set(String key, V value) {
        if(value instanceof String){
            redisTemplate.opsForValue().set(prefix + key,value.toString());
        }else{
            String jsonValue = JSONUtil.toString(value);
            redisTemplate.opsForValue().set(prefix + key,jsonValue);
        }
    }

    @Override
    public void set(String key, V value, long expireSecond) {
        if(expireSecond < 0){
            return;
        }
        if(value instanceof String){
            redisTemplate.opsForValue().set(prefix + key,value.toString(),expireSecond, TimeUnit.SECONDS);
        }else{
            String jsonValue = JSONUtil.toString(value);
            redisTemplate.opsForValue().set(prefix + key,jsonValue,expireSecond, TimeUnit.SECONDS);
        }
    }

    @Override
    public <T> T get(String key,Class<T> type) {
        String value = redisTemplate.opsForValue().get(key);
        if(type == String.class){
            return (T)value;
        }
        return JSONUtil.jsonToObject(value,type);

    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(prefix + key);
    }

    @Override
    public boolean exist(String key) {
        if (null == key) {
            return false;
        }
        Boolean hasKey = redisTemplate.hasKey(prefix + key);
        return null == hasKey ? false : hasKey;
    }

    @Override
    public boolean expire(String key) {
        Long expire = redisTemplate.getExpire(key);  // second expire
        return expire < 0;
    }

    /**
     * 清除所有prefix开头的key
     */
    @Override
    public void clear() {
        Set<String> keys = redisTemplate.keys(prefix);
        redisTemplate.delete(keys);
    }


}
