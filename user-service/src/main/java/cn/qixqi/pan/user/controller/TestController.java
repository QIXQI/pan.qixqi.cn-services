package cn.qixqi.pan.user.controller;


import cn.qixqi.pan.user.config.ServiceConfig;
import cn.qixqi.pan.user.model.TestTable;
import cn.qixqi.pan.user.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private ServiceConfig serviceConfig;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TestTable> getTests(){
        return testService.gets();
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public TestTable getTest(@PathVariable("uid") String uid){
        return testService.getTest(uid);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void saveTest(@RequestBody TestTable testTable){
        testService.saveTest(testTable);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
    public String updateTest(@PathVariable("uid") String uid){
        return String.format("更改 " + uid);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteTest(@PathVariable("uid") String uid){
        return String.format("删除 " + uid);
    }

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String example(){
        return serviceConfig.getExampleProperty();
    }
}
