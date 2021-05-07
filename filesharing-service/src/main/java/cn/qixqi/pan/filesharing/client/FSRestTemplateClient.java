package cn.qixqi.pan.filesharing.client;

import cn.qixqi.pan.filesharing.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FSRestTemplateClient {

    // 支持Ribbon的RestTemplate类
    @Autowired
    private RestTemplate restTemplate;

    public File getFile(String fileId){
        ResponseEntity<File> responseEntity = restTemplate.exchange(
                "http://filesystemservice/filesystem/file/{fileId}",
                HttpMethod.GET,
                null, File.class, fileId
        );
        return responseEntity.getBody();
    }
}
