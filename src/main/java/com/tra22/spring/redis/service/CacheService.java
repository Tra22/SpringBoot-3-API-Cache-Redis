package com.tra22.spring.redis.service;

import com.tra22.spring.redis.exception.NotFoundEntityException;
import com.tra22.spring.redis.service.interf.ICacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class CacheService implements ICacheService {
    private final CacheManager cacheManager;

    @Override
    public void evictCacheValueByKey(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if(cache == null) throw new NotFoundEntityException("Cache not found.");
        cache.evictIfPresent(key);
        log.info("value is evicted from cache {} and key {}", cacheName, key);
    }

    @Override
    public void evictCacheValueByName(String cacheName) {
        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
        log.info("value is evicted from cache {}", cacheName);
    }

    @Override
    public void evictCacheValues() {
        cacheManager.getCacheNames().clear();
        log.info("All cache values is evicted from cache");
    }

    @Override
    public Object getCacheValueByKey(String cacheName, String key) {
        Cache cache = cacheManager.getCache(cacheName);
        if(cache == null) throw new NotFoundEntityException("Cache not found.");
        return cache.get(key, Object.class);
    }
}
