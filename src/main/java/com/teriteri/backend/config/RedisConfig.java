package com.teriteri.backend.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

/**
 * Redis配置类，用于配置Redis连接和缓存相关设置
 * 继承CachingConfigurerSupport以自定义缓存配置
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    // 从配置文件中注入Redis主机地址
    @Value("${spring.redis.host}")
    private String host;

    // 从配置文件中注入Redis端口号
    @Value("${spring.redis.port}")
    private int port;

    // 从配置文件中注入Redis连接超时时间
    @Value("${spring.redis.timeout}")
    private int timeout;

    // 从配置文件中注入Redis连接池最大空闲连接数
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    // 从配置文件中注入Redis连接池最大等待时间
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;

    /**
     * 创建并配置一个 Jedis 连接池
     * @return JedisPool实例，用于管理Redis连接
     */
    @Bean
    @SuppressWarnings("all")
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);  // 设置最大空闲连接数
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);  // 设置最大等待时间
        return new JedisPool(jedisPoolConfig, host, port, timeout);  // 创建并返回JedisPool实例
    }

    /**
     * 缓存键生成器，在这个项目中用不到，我更倾向自己手动命名，如 user:1:age
     * @return 自定义的KeyGenerator实现
     */
    @Bean
    @SuppressWarnings("all")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder SB = new StringBuilder();
                SB.append(target.getClass().getName());  // 添加目标类名
                SB.append(method.getName());  // 添加方法名
                for (Object obj : params) {  // 添加所有参数
                    SB.append(obj.toString());
                }
                return SB.toString();  // 返回生成的键
            }
        };
    }

    /**
     * Redis 缓存管理器
     * @param connectionFactory Redis连接工厂
     * @return 配置好的Redis缓存管理器
     */
    @Bean
    @SuppressWarnings("all")
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).build();
        return redisCacheManager;
    }

    /**
     * 编写自己的 redisTemplate，用于与 Redis 进行交互，配置了json格式存储，序列化与反序列化
     * @param redisConnectionFactory Redis连接工厂
     * @return 配置好的RedisTemplate实例
     */
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 为了方便自己开发，一般直接使用 <String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        // 序列化配置
        // json的序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);    // 用json序列化任意对象类
        ObjectMapper om = new ObjectMapper();       // 对象类序列化过程中用 ObjectMapper 进行转义
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // String的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用了String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    /**
     * 一个专门用于操作 Redis 字符串类型的模板，它是 RedisTemplate 的子类，只支持字符串数据的存储和检索
     * @param factory Redis连接工厂
     * @return 配置好的StringRedisTemplate实例
     */
    @Bean
    @SuppressWarnings("all")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        return stringRedisTemplate;
    }
}