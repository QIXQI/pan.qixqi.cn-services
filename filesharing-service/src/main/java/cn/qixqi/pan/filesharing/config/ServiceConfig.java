package cn.qixqi.pan.filesharing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

    @Value("${example.property}")
    private String exampleProperty;

    @Value("${db.mongodb.rebuild}")
    private boolean dbMongoRebuild;

    @Value("${db.mongodb.init.file_share}")
    private String dbMongoInitFileShare;

    @Value("${db.mongodb.init.file_share_link}")
    private String dbMongoInitFileShareLink;

    @Value("${spring.redis.cluster.nodes}")
    private String redisNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer redisMaxRedirects;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.database}")
    private Integer redisDatabase;

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer redisPoolMaxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer redisPoolMaxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private Long redisPoolMaxWait;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer redisPoolMinIdle;

    @Value("${user-context.key.trace-id}")
    private String ctxKeyTraceId;

    @Value("${user-context.key.auth-token}")
    private String ctxKeyAuthToken;

    @Value("${user-context.key.uid}")
    private String ctxKeyUid;

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    public String getExampleProperty() {
        return exampleProperty;
    }

    public boolean isDbMongoRebuild() {
        return dbMongoRebuild;
    }

    public String getDbMongoInitFileShare() {
        return dbMongoInitFileShare;
    }

    public String getDbMongoInitFileShareLink() {
        return dbMongoInitFileShareLink;
    }

    public String getRedisNodes() {
        return redisNodes;
    }

    public Integer getRedisMaxRedirects() {
        return redisMaxRedirects;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public Integer getRedisDatabase() {
        return redisDatabase;
    }

    public Integer getRedisPoolMaxActive() {
        return redisPoolMaxActive;
    }

    public Integer getRedisPoolMaxIdle() {
        return redisPoolMaxIdle;
    }

    public Long getRedisPoolMaxWait() {
        return redisPoolMaxWait;
    }

    public Integer getRedisPoolMinIdle() {
        return redisPoolMinIdle;
    }

    public String getCtxKeyTraceId() {
        return ctxKeyTraceId;
    }

    public String getCtxKeyAuthToken() {
        return ctxKeyAuthToken;
    }

    public String getCtxKeyUid() {
        return ctxKeyUid;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }
}
