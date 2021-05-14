package cn.qixqi.pan.filesharing;

import cn.qixqi.pan.filesharing.config.ServiceConfig;
import cn.qixqi.pan.filesharing.event.InBoundChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(InBoundChannel.class)
@EnableCircuitBreaker                   // Hystrix 断路器模式
@EnableResourceServer
public class FileShareApplication{

    private static final Logger logger = LoggerFactory.getLogger(FileShareApplication.class);

    @Autowired
    private ServiceConfig config;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 收到来自 input 通道的消息后，Spring Cloud Stream 执行此方法
     * @param msg
     */
    @StreamListener("inBoundFS")
    public void loggerSink(String msg){
        System.out.println("Msg: " + msg + "----------------------------------------------------------");
        logger.debug("报告长官，接收到消息：() " + msg);
    }

    /**
     * 支持Ribbon负载均衡功能的服务发现
     * @return
     */
    @LoadBalanced       // 该注解表示：创建一个支持Ribbon的RestTemplate类
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 初始化 Mongodb 数据库
     * @return
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator(){
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        if (config.isDbMongoRebuild()){
            // 重新生成数据库数据
            // 初始化 fileShare 集合
            Resource initFileShare = new ClassPathResource(config.getDbMongoInitFileShare());
            // 初始化 fileShareLink 集合
            Resource initFileShareLink = new ClassPathResource(config.getDbMongoInitFileShareLink());
            factory.setResources(new Resource[]{initFileShare, initFileShareLink});
        } else {
            factory.setResources(new Resource[]{});
        }
        return factory;
    }

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
        SpringApplication.run(FileShareApplication.class, args);
    }
}