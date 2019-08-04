package com.cmssystem.useradmin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@SpringBootApplication
@EnableCaching
public class UseradminApplication {

    private String redisHost = "localhost";
    private int redisPort = 6379;


    @Value("#{${spring.cache.redis.time-to-live}}")
    private int DEFAULT_TTL;

    public static void main(String[] args) {
        SpringApplication.run(UseradminApplication.class, args);
    }


//        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
//        jedisConFactory.setHostName(redisHost);
//        jedisConFactory.setPort(redisPort);
//        return jedisConFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, UserAdminToken> redisTemplate() {
//        RedisTemplate<String, UserAdminToken> template = new RedisTemplate<String, UserAdminToken>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        return template;
//    }
//
//    @Bean("redisCacheManager")
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        cacheManager.setDefaultExpiration(DEFAULT_TTL);
//        return cacheManager;
//    }

    @Bean("redisCacheManager")
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10));
        return new RedisCacheManager(RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory), redisCacheConfiguration);
    }
}


