package com.ranger.defender.cache.memory;

import com.ranger.defender.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author ranger
 * @Date 2020/6/7 12:12
 **/
public class MapMemoryCache<K,V> implements Cache<K,V> {

    private Map<K,V> cache;

    public MapMemoryCache(){
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * 设置一个容器初始大小
     * @param capacity
     */
    public MapMemoryCache(int capacity){
        this.cache = new ConcurrentHashMap<>(capacity);
    }

    @Override
    public void set(K key, V value) {
        cache.put(key,value);
    }

    @Override
    public void set(K key, V value, long expire) {
        cache.put(key,value);
    }

    @Override
    public <T> T get(K key,Class<T> type) {
        return (T)cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public boolean exist(K key) {
        return cache.containsKey(key);
    }

    @Override
    public boolean expire(K key) {
        return !cache.containsKey(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
