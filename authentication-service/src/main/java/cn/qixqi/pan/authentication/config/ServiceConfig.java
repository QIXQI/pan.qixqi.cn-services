package cn.qixqi.pan.authentication.config;

import cn.qixqi.pan.authentication.model.UserPriority;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
public class ServiceConfig {

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    private final UserPriority PRIORITY_NORMAL_USER = new UserPriority(0, "NORMAL_USER");
    private final UserPriority PRIORITY_VIP_USER = new UserPriority(1, "VIP_USER");

    private final Map<Integer, String> PRIORITYMAP = new HashMap<Integer, String>();

    @Value("${user-context.key.trace-id}")
    private String ctxKeyTraceId;

    @Value("${user-context.key.auth-token}")
    private String ctxKeyAuthToken;

    @Value("${user-context.key.uid}")
    private String ctxKeyUid;

    @PostConstruct
    private void init(){
        PRIORITYMAP.put(PRIORITY_NORMAL_USER.getPriorityId(), PRIORITY_NORMAL_USER.getPriorityName());
        PRIORITYMAP.put(PRIORITY_VIP_USER.getPriorityId(), PRIORITY_VIP_USER.getPriorityName());
    }

    public String getJwtSigningKey(){
        return this.jwtSigningKey;
    }

    public Map<Integer, String> getPRIORITYMAP() {
        return this.PRIORITYMAP;
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
}
