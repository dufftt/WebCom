package com.duft.order_service.Util.RedisConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.ObjectMapper;

public class RedisUtil {

    public final static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();
    

    public static <T> void addCache(String key, T value){ 
            try {
                String valueToBeCached = mapper.writeValueAsString(value);
                RedisConfiguration.getRedisCommands().set(key, valueToBeCached);
                logger.info("Redis Cache Updated: Key: {} Value: {}",key,valueToBeCached);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

      public static <T> T getCache(String key, Class<T> targetClass){
        String value = null;
        T valueTakenFromCache = null;
            try {    
                value = RedisConfiguration.getRedisCommands().get(key);
                if (value == null) return null;
                valueTakenFromCache = mapper.readValue(value, targetClass);
                logger.info("Redis Cache received: Key: {} Value: {}",key,valueTakenFromCache);
            } catch (Exception e) {
                e.printStackTrace();
            }
             return valueTakenFromCache;
    }

    public static void deleteCache(String key){
            try {
                RedisConfiguration.getRedisCommands().del(key);
                logger.info("Redis Cache Deleted: Key: {}",key);
                } catch (Exception e) {
                e.printStackTrace();
            }
            
    }
   
}
