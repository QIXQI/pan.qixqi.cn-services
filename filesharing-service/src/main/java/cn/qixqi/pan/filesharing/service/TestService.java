package cn.qixqi.pan.filesharing.service;

import cn.qixqi.pan.filesharing.client.FSRestTemplateClient;
import cn.qixqi.pan.filesharing.model.File;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private FSRestTemplateClient fsRestTemplateClient;

    /**
     * 断路器模式
     * @param fileId
     * @return
     */
    @HystrixCommand
    public File getFile(String fileId){
        return fsRestTemplateClient.getFile(fileId);
    }
}
