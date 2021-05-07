package cn.qixqi.pan.filesharing.controller;

import cn.qixqi.pan.filesharing.model.File;
import cn.qixqi.pan.filesharing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "filesharing")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/file/{fileId}", method = RequestMethod.GET)
    public File getFile(@PathVariable String fileId){
        return testService.getFile(fileId);
    }
}
