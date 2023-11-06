package com.tra22.spring.redis.config;

import java.time.Duration;

import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.util.StringUtils;

@Configuration
@ConfigurationProperties(prefix  = "redis")
public class RedisConfig {
  @Setter
  private String host;
  @Setter
  private int port;
  @Setter
  private String username;
  @Setter
  private String password;
  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
//    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(host);
    configuration.setPort(port);
    if(StringUtils.hasText(username) && StringUtils.hasText(password)) {
      configuration.setUsername(username);
      configuration.setPassword(password);
    }
    return new LettuceConnectionFactory(configuration);
  }
  @Bean
  public RedisCacheManager cacheManager() {
    RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

    return RedisCacheManager.builder(redisConnectionFactory())
        .cacheDefaults(cacheConfig)
        .withCacheConfiguration("books", myDefaultCacheConfig(Duration.ofMinutes(5)))
        .withCacheConfiguration("book", myDefaultCacheConfig(Duration.ofMinutes(1)))
        .build();
  }

  private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
    return RedisCacheConfiguration
        .defaultCacheConfig()
        .entryTtl(duration)
        .serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
  }
}
