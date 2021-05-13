package cn.qixqi.pan.fs;

import cn.qixqi.pan.fs.config.ServiceConfig;
import cn.qixqi.pan.fs.event.OutBoundChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import redis.clients.jedis.JedisPoolConfig;


@SpringBootApplication
@EnableBinding(OutBoundChannel.class)        // 将程序绑定到消息代理
@EnableCircuitBreaker                        // Hystrix 断路器模式
@EnableResourceServer
public class FileSystemApplication {

    @Autowired
    private ServiceConfig config;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 初始化 Mongodb 数据库
     * @return
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(){
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        if (config.isDbMongoRebuild()){
            // 重新生成集合
            // 初始化文件实体
            Resource initFile = new ClassPathResource(config.getDbMongoInitFile());
            // 初始化文件夹链接
            Resource initFolderLink = new ClassPathResource(config.getDbMongoInitFolderLink());
            factory.setResources(new Resource[]{initFolderLink});
        } else {
            factory.setResources(new Resource[]{});
        }
        return factory;
    }

    /**
     * 设置到Redis服务器的实际数据库连接
     * @return
     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(){
//        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration();
//        standaloneConfiguration.setHostName(config.getRedisServer());
//        standaloneConfiguration.setPort(config.getRedisPort());
//        standaloneConfiguration.setPassword(config.getRedisPassword());
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfiguration);
//        return jedisConnectionFactory;
//    }

    /**
     * 创建 RedisTemplate，对Redis服务器执行操作
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        // 序列化，防止使用默认jdk序列化，造成乱码
        RedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }

    public static void main(String[] args){
        SpringApplication.run(FileSystemApplication.class, args);
    }
}
