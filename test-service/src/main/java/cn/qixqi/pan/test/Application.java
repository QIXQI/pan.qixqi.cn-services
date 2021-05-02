package cn.qixqi.pan.test;


import cn.qixqi.pan.test.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "test")
public class Application {

    @Autowired
    private ServiceConfig serviceConfig;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String example(){
        return serviceConfig.getExampleProperty();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String hello(){
        return String.format("愿世间再无bug");
    }
}
