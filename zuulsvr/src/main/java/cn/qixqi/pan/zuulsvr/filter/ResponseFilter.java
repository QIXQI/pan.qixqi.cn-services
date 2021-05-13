package cn.qixqi.pan.zuulsvr.filter;

import cn.qixqi.pan.zuulsvr.config.ServiceConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);

    @Autowired
    private FilterUtil filterUtil;

    @Autowired
    private ServiceConfig config;

    @Override
    public String filterType() {
        return FilterUtil.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        logger.debug("Zuulsvr 在响应中添加 trace_id: {}", filterUtil.getTraceId());
        context.getResponse().addHeader(
                config.getCtxKeyTraceId(),
                filterUtil.getTraceId());

        logger.debug("响应{}完成", context.getRequest().getRequestURI());
        return null;
    }
}
