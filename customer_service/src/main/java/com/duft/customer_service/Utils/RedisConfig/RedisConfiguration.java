package com.duft.customer_service.Utils.RedisConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Configuration
public class RedisConfiguration {
    private static RedisCommands<String, String> redisCommands;

    public static RedisCommands<String, String> getRedisCommands() {
        return redisCommands;
    }

    @Bean(destroyMethod = "shutdown")
    public RedisClient redisClient(){
        RedisURI redisURI = RedisURI.Builder.redis("localhost",6379).build();
        return RedisClient.create(redisURI);
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
