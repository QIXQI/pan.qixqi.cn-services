package cn.qixqi.pan.fs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {
    @Value("${example.property}")
    private String exampleProperty;

    @Value("${db.mongodb.rebuild}")
    private boolean dbMongoRebuild;

    @Value("${db.mongodb.init.file}")
    private String dbMongoInitFile;

    @Value("${db.mongodb.init.file_md5}")
    private String dbMongoInitFileMd5;

    @Value("${db.mongodb.init.folder_link}")
    private String dbMongoInitFolderLink;

//    @Value("${redis.server}")
//    private String redisServer;
//
//    @Value("${redis.port}")
//    private String redisPort;
//
//    @Value("${redis.password}")
//    private String redisPassword;

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

    public String getExampleProperty(){
        return this.exampleProperty;
    }

    public boolean isDbMongoRebuild(){
        return this.dbMongoRebuild;
    }

    public String getDbMongoInitFile(){
        return this.dbMongoInitFile;
    }

    public String getDbMongoInitFileMd5(){
        return this.dbMongoInitFileMd5;
    }

    public String getDbMongoInitFolderLink(){
        return this.dbMongoInitFolderLink;
    }

//    public String getRedisServer(){
//        return this.redisServer;
//    }
//
//    public Integer getRedisPort(){
//        return new Integer(this.redisPort).intValue();
//    }
//
//    public String getRedisPassword(){
//        return this.redisPassword;
//    }
    public String getRedisNodes(){
        return this.redisNodes;
    }

    public Integer getRedisMaxRedirects(){
        return this.redisMaxRedirects;
    }

    public String getRedisPassword(){
        return this.redisPassword;
    }

    public Integer getRedisDatabase() {
        return this.redisDatabase;
    }

    public Integer getRedisPoolMaxActive() {
        return this.redisPoolMaxActive;
    }

    public Integer getRedisPoolMaxIdle() {
        return this.redisPoolMaxIdle;
    }

    public Long getRedisPoolMaxWait() {
        return this.redisPoolMaxWait;
    }

    public Integer getRedisPoolMinIdle() {
        return this.redisPoolMinIdle;
    }

    public String getCtxKeyTraceId(){
        return this.ctxKeyTraceId;
    }

    public String getCtxKeyAuthToken(){
        return this.ctxKeyAuthToken;
    }

    public String getCtxKeyUid(){
        return this.ctxKeyUid;
    }

    public String getJwtSigningKey(){
        return this.jwtSigningKey;
    }
}
