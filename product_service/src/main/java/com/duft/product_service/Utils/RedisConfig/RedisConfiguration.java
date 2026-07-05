package com.duft.product_service.Utils.RedisConfig;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Configuration
public class RedisConfiguration {
    private static RedisCommands<String, String> redisCommands;

     @Value("${REDIS_URL}")
    private String redisUrl;


    public static RedisCommands<String, String> getRedisCommands() {
        return redisCommands;
    }

    @Bean(destroyMethod = "shutdown")
    public RedisClient redisClient(){
        return RedisClient.create(redisUrl);
    }
    @Bean(destroyMethod = "close")
    public StatefulRedisConnection<String, String> connection(RedisClient redisClient){
        return redisClient.connect();
    }
    @Bean
    public RedisCommands<String, String> commands(StatefulRedisConnection<String, String> connection){
            RedisCommands<String, String> commands = connection.sync();
            redisCommands = commands;
            return commands;
    }
}
