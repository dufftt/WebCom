package com.duft.customer_service.Utils.RedisConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PreDestroy;

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

    @EventListener(ContextClosedEvent.class)
    public void clearRedisCacheOnShutdown() {
        System.out.println("Clearing Redis cache before application shutdown...");
        
        try {
            // FLUSHDB deletes all keys of the currently selected database
            redisCommands.flushdb();
            // If you want to clear all keys from all databases, use flushAll()
            // connection.flushAll(); 
            System.out.println("Redis cache cleared successfully.");
        } catch (Exception e) {
            System.err.println("Error clearing Redis cache: " + e.getMessage());
        }
    }
}
