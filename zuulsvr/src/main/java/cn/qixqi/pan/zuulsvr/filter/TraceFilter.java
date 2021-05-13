package cn.qixqi.pan.zuulsvr.filter;

import cn.qixqi.pan.zuulsvr.config.ServiceConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 前置过滤器：
 */
@Component
public class TraceFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    private static final Logger logger = LoggerFactory.getLogger(TraceFilter.class);

    @Autowired
    private FilterUtil filterUtil;

    @Autowired
    private ServiceConfig config;

    /**
     * 过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return FilterUtil.PRE_FILTER_TYPE;
    }

    /**
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    /**
     * 过滤器是否执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        // 解析 trace_id
        if (filterUtil.getTraceId() != null){
            logger.debug("TraceFilter获取 trace_id: {}", filterUtil.getTraceId());
        } else {
            String traceId = UUID.randomUUID().toString();
            filterUtil.setTraceId(traceId);
            logger.debug("TraceFilter生成 trace_Id: {}", filterUtil.getTraceId());
        }
        // 解析 uid
        parseUid();

        RequestContext context = RequestContext.getCurrentContext();
        logger.debug("Zuulsvr 接收到来自{}的请求.", context.getRequest().getRequestURI());
        return null;
    }

    /**
     * 从jwt令牌中解析出uid
     * @return
     */
    private void parseUid(){
        if (filterUtil.getAuthToken() != null){
            String authToken = filterUtil.getAuthToken().replace("Bearer ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(config.getJwtSigningKey().getBytes("UTF-8"))
                        .parseClaimsJws(authToken)
                        .getBody();
                String uid = (String) claims.get("uid");
                if (uid != null){
                    filterUtil.setUid(uid);
                    logger.debug("解析jwt令牌成功，uid: {}", filterUtil.getUid());
                } else {
                    logger.debug("从jwt令牌中解析uid失败，auth_token: {}", authToken);
                }
            } catch (Exception ex){
                logger.error("解析jwt令牌，获取uid异常，异常信息：{}", ex);
            }
        } else {
            logger.warn("没有验证信息");
        }
    }
}
