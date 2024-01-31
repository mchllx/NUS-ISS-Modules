package sg.edu.nus.iss.d25workshop.configs;

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
import org.springframework.data.redis.serializer.StringRedisSerializer;

import sg.edu.nus.iss.d25workshop.services.SalesSubscribe;

@Configuration
public class AppConfig {
    @Autowired
    SalesSubscribe salesSub;

    private Logger logger = Logger.getLogger(AppConfig.class.getName());

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
    public JedisConnectionFactory createFactory() {

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

    @Bean
    public RedisTemplate<String, String> createRedisConnection() {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(createFactory());

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        return template;
    }

    @Bean("myredis")
    public RedisMessageListenerContainer createListenerContainer() {
        JedisConnectionFactory jedisFac = createFactory();
        
        RedisMessageListenerContainer cont = new RedisMessageListenerContainer();
        cont.setConnectionFactory(jedisFac);

        cont.addMessageListener(salesSub, ChannelTopic.of("sales"));
        return cont;
    }
    
}
