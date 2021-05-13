package cn.qixqi.pan.fs.util;

import cn.qixqi.pan.fs.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Autowired
    private ServiceConfig config;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        UserContextHolder.get().setTraceId(
                httpServletRequest.getHeader(config.getCtxKeyTraceId()));
        UserContextHolder.get().setAuthToken(
                httpServletRequest.getHeader(config.getCtxKeyAuthToken()));
        UserContextHolder.get().setUid(
                httpServletRequest.getHeader(config.getCtxKeyUid()));

        logger.debug("UserContext trace_id: {}", UserContextHolder.get().getTraceId());
        logger.debug("UserContext auth_token: {}", UserContextHolder.get().getAuthToken());
        logger.debug("UserContext uid: {}", UserContextHolder.get().getUid());

        chain.doFilter(httpServletRequest, response);
    }
}
