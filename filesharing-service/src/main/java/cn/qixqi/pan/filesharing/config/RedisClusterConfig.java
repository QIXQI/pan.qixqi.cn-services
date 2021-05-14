package cn.qixqi.pan.filesharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RedisClusterConfig {

    @Autowired
    private ServiceConfig config;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig,
                                                         RedisClusterConfiguration clusterConfiguration){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(clusterConfiguration, jedisPoolConfig);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(config.getRedisPoolMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(config.getRedisPoolMaxWait());
        jedisPoolConfig.setMaxTotal(config.getRedisPoolMaxActive());
        jedisPoolConfig.setMinIdle(config.getRedisPoolMinIdle());
        return jedisPoolConfig;
    }

    @Bean
    public RedisClusterConfiguration clusterConfiguration(){
        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration();

        String[] sub = config.getRedisNodes().split(",");
        List<RedisNode> nodeList = new ArrayList<>(sub.length);
        String[] tmp;
        for (String s : sub){
            tmp = s.split(":");
            nodeList.add(new RedisNode(tmp[0], Integer.valueOf(tmp[1])));
        }

        clusterConfig.setClusterNodes(nodeList);
        clusterConfig.setMaxRedirects(config.getRedisMaxRedirects());
        clusterConfig.setPassword(config.getRedisPassword());
        return clusterConfig;
    }
}
