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

    @Value("${db.mongodb.init.folder_link}")
    private String dbMongoInitFolderLink;

    @Value("${redis.server}")
    private String redisServer;

    @Value("${redis.port}")
    private String redisPort;

    @Value("${redis.password}")
    private String redisPassword;

    @Value("${user-context.key.trace-id}")
    private String ctxKeyTraceId;

    @Value("${user-context.key.auth-token}")
    private String ctxKeyAuthToken;

    @Value("${user-context.key.uid}")
    private String ctxKeyUid;

    public String getExampleProperty(){
        return this.exampleProperty;
    }

    public boolean isDbMongoRebuild(){
        return this.dbMongoRebuild;
    }

    public String getDbMongoInitFile(){
        return this.dbMongoInitFile;
    }

    public String getDbMongoInitFolderLink(){
        return this.dbMongoInitFolderLink;
    }

    public String getRedisServer(){
        return this.redisServer;
    }

    public Integer getRedisPort(){
        return new Integer(this.redisPort).intValue();
    }

    public String getRedisPassword(){
        return this.redisPassword;
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
}
