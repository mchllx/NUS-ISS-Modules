package com.example.backend.configs;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.backend.Utils;

// import vttp2023.batch4.paf.day25emart.services.PurchaseOrderSubscriber;

@Configuration
public class RedisConfig {

    // @Autowired
    // PurchaseOrderSubscriber poSub;

    private Logger logger = Logger.getLogger(RedisConfig.class.getName());

    //application.properties
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private Integer redisPort;

    @Value("${spring.redis.database}")
    private Integer redisDatabase;

    @Value("${spring.redis.username}")
    private String redisUsername;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Bean
    public JedisConnectionFactory createJedisConnection() {

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        config.setDatabase(redisDatabase);

        if (!redisUsername.isEmpty()) {
            config.setUsername(redisUsername);
        }
        if (!redisPassword.isEmpty()) {
            config.setPassword(redisPassword);
        }
       
        logger.log(Level.INFO, "Redis database: %d".formatted(redisPort));

        JedisClientConfiguration jedisClient = JedisClientConfiguration
            .builder().build();

        //if indirectly references, inject jedis dependency 
        JedisConnectionFactory jedisFac = new JedisConnectionFactory(config, jedisClient);
            jedisFac.afterPropertiesSet();

        return jedisFac;
    }

    @Bean(Utils.BEAN_REDIS)
    public RedisTemplate<String, String> createRedisConnection() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(createJedisConnection());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        return template;
    }

    // @Bean("poPubSub")
    // ChannelTopic topic() {
    //     return new ChannelTopic("po-channel");
    // }

    // @Bean
    // MessageListenerAdapter messageListener() {
    //     return new MessageListenerAdapter(poSub);
    // }

    // @Bean
    // RedisMessageListenerContainer redisContainer() {
    //     RedisMessageListenerContainer cont = new RedisMessageListenerContainer();
    //     cont.setConnectionFactory(createJedisConnection());
    //     cont.addMessageListener(messageListener(), topic());

    //     return cont;
    // }
    
}
