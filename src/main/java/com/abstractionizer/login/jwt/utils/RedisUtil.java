package com.abstractionizer.login.jwt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@AllArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public boolean set(@NonNull String key, @NonNull Object obj, @NonNull Long expiration, @NonNull TimeUnit timeUnit){
        if(key == null){
            throw new NullPointerException("Key is marked non-null but is null");
        }else if (obj == null){
            throw new NullPointerException("Object is marked non-null but is null");
        }else if (expiration == null){
            throw new NullPointerException("Expiration is marked non-null but is null");
        }else if (timeUnit == null){
            throw new NullPointerException("Time unit is marked non-null but is null");
        }else{
            try{
                BoundValueOperations<String, Object> boundValueOperations = this.redisTemplate.boundValueOps(key);
                boundValueOperations.set(obj, expiration, timeUnit);
                return true;
            }catch(Exception e){
                try{
                    log.info("Write obj to redis failed, key: {} obj: {} ", key, this.objectMapper.writeValueAsString(obj), e);
                } catch (JsonProcessingException ex) {
                    log.error("RedisUtil write obj to json error: ", ex);
                }
                return false;
            }
        }
    }

    public <T> T get(@NonNull String key, @NonNull Class<T> cls){
        if(key == null){
            throw new NullPointerException("Key is marked non-null but is null");
        }else if (cls == null){
            throw new NullPointerException("Cls is marked non-null but is null");
        }else{
            Object obj;
            try{
                BoundValueOperations<String, Object> boundValueOperations = this.redisTemplate.boundValueOps(key);
                obj = boundValueOperations.get();
            }catch(Exception e){
                log.error("Get obj failed, key: " + key, e);
                return null;
            }
            return cls.cast(obj);
        }
    }

    public Long increment(@NonNull String key, @NonNull Long amount){
        if(key == null){
            throw new NullPointerException("Key is marked non-null but is null");
        }else if(amount == null){
            throw new NullPointerException("Amount is marked non-null but is null");
        }else {
            return this.redisTemplate.opsForValue().increment(key, amount);
        }
    }

    public boolean isKeyExists(@NonNull String key){
        if(key == null){
            throw new NullPointerException("Key is marked non-null but is null");
        }else if (ObjectUtils.isEmpty(key)){
            return false;
        }else {
            Object obj = this.get(key, Object.class);
            return Objects.nonNull(obj);
        }
    }

    public Long seCountAndExpiration(@NonNull String key, @NonNull Long count, @NonNull Long time, @NonNull TimeUnit timeUnit){
        if(key == null){
            throw new NullPointerException("Key is marked non-null but is null");
        }else if (count == null){
            throw new NullPointerException("Count is marked non-null but is null");
        }else if (time == null){
            throw new NullPointerException("Time is marked non-null but is null");
        }else if (timeUnit == null){
            throw new NullPointerException("Time unit is marked non-null but is null");
        }else{
            redisTemplate.opsForValue().set(key, count, time, timeUnit);
            return count;
        }
    }

    public boolean deleteKey(@NonNull String key){
        return Objects.isNull(key) || this.redisTemplate.delete(key);
    }
}
