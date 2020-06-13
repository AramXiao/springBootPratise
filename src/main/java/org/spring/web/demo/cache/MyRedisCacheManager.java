package org.spring.web.demo.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.util.StringUtils;

import java.time.Duration;

public class MyRedisCacheManager extends RedisCacheManager {


    public MyRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultConfiguration){
        super(cacheWriter, defaultConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        if (!StringUtils.isEmpty(name) && name.contains("#")) {
            String numStr = name.split("#")[1];
            if ("^[0-9]*$".matches(numStr)) {
                //默认单位为秒，可根据需求拓展
                return super.createRedisCache(name, cacheConfig.entryTtl(Duration.ofSeconds(Integer.parseInt(numStr))));
            }
        }
        //不包含则走默认时间
        return super.createRedisCache(name, cacheConfig);
    }
}
