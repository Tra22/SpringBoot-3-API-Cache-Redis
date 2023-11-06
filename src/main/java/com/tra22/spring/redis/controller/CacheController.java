package com.tra22.spring.redis.controller;

import com.tra22.spring.redis.payload.global.Response;
import com.tra22.spring.redis.service.interf.ICacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/caches")
@RequiredArgsConstructor
public class CacheController {
    private final ICacheService cacheService;
    @PostMapping("/{cacheName}")
    public ResponseEntity<Response<?>> clearCacheByCacheName(@PathVariable String cacheName){
        cacheService.evictCacheValueByName(cacheName);
        return new ResponseEntity<>(Response.okWithNoContent(), HttpStatus.NO_CONTENT);
    }
    @PostMapping("/{cacheName}/{key}")
    public ResponseEntity<Response<?>> clearCacheByCacheName(@PathVariable String cacheName, @PathVariable String key){
        cacheService.evictCacheValueByKey(cacheName, key);
        return new ResponseEntity<>(Response.okWithNoContent(), HttpStatus.NO_CONTENT);
    }
    @PostMapping
    public ResponseEntity<Response<?>> clearAllCache(){
        cacheService.evictCacheValues();
        return new ResponseEntity<>(Response.okWithNoContent(), HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<Response<?>> getCacheByKey(String cacheName, String key){
        Object obj = cacheService.getCacheValueByKey(cacheName, key);
        return new ResponseEntity<>(Response.ok(obj), HttpStatus.OK);
    }

}
