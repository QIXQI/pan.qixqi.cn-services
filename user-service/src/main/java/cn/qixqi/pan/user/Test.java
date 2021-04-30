package cn.qixqi.pan.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "hello")
public class Test {

    public static void main(String[] args){
        SpringApplication.run(Test.class, args);
    }

    @RequestMapping(value = "/{first}/{second}", method = RequestMethod.GET)
    public String hello(@PathVariable("first") String first, @PathVariable("second") String second){
        return String.format("{\"message\": \"Hello %s %s\"}", first, second);
    }
}
