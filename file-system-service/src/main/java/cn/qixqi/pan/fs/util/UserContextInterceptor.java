package cn.qixqi.pan.fs.util;

import cn.qixqi.pan.fs.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class UserContextInterceptor implements ClientHttpRequestInterceptor {
    @Autowired
    private ServiceConfig config;

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();
        headers.add(config.getCtxKeyTraceId(), UserContextHolder.get().getTraceId());
        headers.add(config.getCtxKeyAuthToken(), UserContextHolder.get().getAuthToken());
        headers.add(config.getCtxKeyUid(), UserContextHolder.get().getUid());

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
