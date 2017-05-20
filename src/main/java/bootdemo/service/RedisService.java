package bootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2017/5/20.
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String ,?> template;

    public boolean set(final String key,final String value) throws Exception{
        boolean result = template.execute((RedisCallback<Boolean>) redisConnection -> {
            RedisSerializer<String> serializer = template.getStringSerializer();
            redisConnection.set(serializer.serialize(key),serializer.serialize(value));
            return true;
        });
        return result;
    }

    public String get(final String key)throws Exception{
        String  result = template.execute((RedisCallback<String>) redisConnection -> {
            RedisSerializer<String> serializer = template.getStringSerializer();
            byte[] bytes = redisConnection.get(serializer.serialize(key));
            return  serializer.deserialize(bytes);
        });
        return result;
    }
}
