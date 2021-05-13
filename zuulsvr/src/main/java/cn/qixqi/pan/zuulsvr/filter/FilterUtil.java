package cn.qixqi.pan.zuulsvr.filter;

import cn.qixqi.pan.zuulsvr.config.ServiceConfig;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilterUtil {

    @Autowired
    private ServiceConfig config;

    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";

    public final String getTraceId(){
        RequestContext context = RequestContext.getCurrentContext();
        // 检查是否在已经传入的请求HTTP首部设置了 "trace_id"
        if (context.getRequest().getHeader(config.getCtxKeyTraceId()) != null){
            return context.getRequest().getHeader(config.getCtxKeyTraceId());
        } else {
            // "trace_id"不存在，检查ZuulRequestHeaders
            return context.getZuulRequestHeaders().get(config.getCtxKeyTraceId());
        }
    }

    public void setTraceId(String traceId){
        // Zuul 不允许直接添加或修改请求HTTP首部，添加到单独的HTTP首部映射，调用服务时合并
        RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader(config.getCtxKeyTraceId(), traceId);
    }

    public final String getAuthToken(){
        RequestContext context = RequestContext.getCurrentContext();
        if (context.getRequest().getHeader(config.getCtxKeyAuthToken()) != null){
            return context.getRequest().getHeader(config.getCtxKeyAuthToken());
        } else {
            return context.getZuulRequestHeaders().get(config.getCtxKeyAuthToken());
        }
    }

    public void setAuthToken(String authToken){
        RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader(config.getCtxKeyAuthToken(), authToken);
    }

    public final String getUid(){
        RequestContext context = RequestContext.getCurrentContext();
        if (context.getRequest().getHeader(config.getCtxKeyUid()) != null){
            return context.getRequest().getHeader(config.getCtxKeyUid());
        } else {
            return context.getZuulRequestHeaders().get(config.getCtxKeyUid());
        }
    }

    public void setUid(String uid){
        RequestContext context = RequestContext.getCurrentContext();
        context.addZuulRequestHeader(config.getCtxKeyUid(), uid);
    }
}
