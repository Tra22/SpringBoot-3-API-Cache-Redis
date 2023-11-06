package com.tra22.spring.redis.service.interf;

public interface ICacheService {
    void evictCacheValueByKey(String cacheName, String key);
    void evictCacheValueByName(String cacheName);
    void evictCacheValues();
    Object getCacheValueByKey(String cacheName, String key);
}
