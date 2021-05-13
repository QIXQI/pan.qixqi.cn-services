package cn.qixqi.pan.user.config;

import cn.qixqi.pan.user.model.UserPriority;
import cn.qixqi.pan.user.model.UserStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ServiceConfig {
    @Value("${example.property}")
    private String exampleProperty;

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    private final UserStatus STATUS_OFFLINE = new UserStatus(0, "OFFLINE");
    private final UserStatus STATUS_ONLINE = new UserStatus(1, "ONLINE");

    private final UserPriority PRIORITY_NORMAL_USER = new UserPriority(0, "NORMAL_USER");
    private final UserPriority PRIORITY_VIP_USER = new UserPriority(1, "VIP_USER");
    private final UserPriority PRIORITY_ADMIN = new UserPriority(2, "ADMIN");

    public String getExampleProperty(){
        return this.exampleProperty;
    }

    public String getJwtSigningKey(){
        return this.jwtSigningKey;
    }

    public UserStatus getSTATUS_OFFLINE(){
        return this.STATUS_OFFLINE;
    }

    public UserStatus getSTATUS_ONLINE(){
        return this.STATUS_ONLINE;
    }

    public UserPriority getPRIORITY_NORMAL_USER(){
        return this.PRIORITY_NORMAL_USER;
    }

    public UserPriority getPRIORITY_VIP_USER(){
        return this.PRIORITY_VIP_USER;
    }

    public UserPriority getPRIORITY_ADMIN(){
        return this.PRIORITY_ADMIN;
    }
}
