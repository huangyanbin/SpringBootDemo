package bootdemo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;



@Configuration
@EnableAutoConfiguration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{


    /**
     * 生成缓存规则
     * @return
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for(Object o :objects){
                sb.append(o.toString());
            }
            return sb.toString();
        };
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis")
    public JedisPoolConfig getRedisConfig(){
        return new JedisPoolConfig();
    }

    @Bean
   @ConfigurationProperties(prefix = "spring.redis")
   JedisConnectionFactory getConnectionFactory() {
       JedisConnectionFactory factory =  new JedisConnectionFactory();
       JedisPoolConfig config = getRedisConfig();
       factory.setPoolConfig(config);
       return factory;
    }

   @Bean
    public RedisTemplate<?,?> getRedisTemplate(){
        RedisTemplate<?,?> template = new RedisTemplate<>();
        template.setConnectionFactory(getConnectionFactory());
        return template;
   }

   @Bean
   public CacheManager cacheManager(RedisTemplate redisTemplate){
       return new RedisCacheManager(redisTemplate);
   }
}
