package com.ranger.defender.cache;

/**
 * @Author ranger
 * @Date 2020/6/7 11:53
 * defender 缓存接口，用于存储认证信息，授权信息等
 **/

public interface Cache<K,V> {
    /**
     * 设置缓存
     * @param key
     * @param value
     */
    void set(K key,V value);

    /**
     * 设计一个带有过期时间的缓存
     * @param key
     * @param value
     * @param expire
     */
    void set(K key,V value,long expire);

    /**
     * 通过key获取设置缓存的指
     * @param key
     * @return
     */
    <T> T get(K key,Class<T> type);

    /**
     * 删除缓存
     * @param key
     */
    void remove(K key);

    /**
     * 判断缓存是否存在
     * @param key
     * @return
     */
    boolean exist(K key);

    /**
     * 判断缓存是否过期  expire-true, otherwise false
     * @param key
     * @return
     */
    boolean expire(K key);

    /**
     * 清空所有缓存
     */
    void clear();

}
