package cn.qixqi.pan.zuulsvr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConfig {

    @Value("${user-context.key.trace-id}")
    private String ctxKeyTraceId;

    @Value("${user-context.key.auth-token}")
    private String ctxKeyAuthToken;

    @Value("${user-context.key.uid}")
    private String ctxKeyUid;

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    public String getCtxKeyTraceId() {
        return ctxKeyTraceId;
    }

    public String getCtxKeyAuthToken() {
        return ctxKeyAuthToken;
    }

    public String getCtxKeyUid() {
        return ctxKeyUid;
    }

    public String getJwtSigningKey(){
        return this.jwtSigningKey;
    }
}
