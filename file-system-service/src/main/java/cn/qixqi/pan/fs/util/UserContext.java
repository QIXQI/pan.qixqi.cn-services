package cn.qixqi.pan.fs.util;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

    private String traceId = new String();
    private String authToken = new String();
    private String uid = new String();

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
