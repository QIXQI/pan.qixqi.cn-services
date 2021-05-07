package cn.qixqi.pan.filesharing;

import cn.qixqi.pan.filesharing.event.InBoundChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableBinding(InBoundChannel.class)
@EnableCircuitBreaker                   // Hystrix 断路器模式
public class FileShareApplication{

    private static final Logger logger = LoggerFactory.getLogger(FileShareApplication.class);

    /**
     * 收到来自 input 通道的消息后，Spring Cloud Stream 执行此方法
     * @param msg
     */
    @StreamListener("inBoundFS")
    public void loggerSink(String msg){
        System.out.println("Msg: " + msg + "----------------------------------------------------------");
        logger.debug("报告长官，接收到消息：() " + msg);
    }

    /**
     * 支持Ribbon负载均衡功能的服务发现
     * @return
     */
    @LoadBalanced       // 该注解表示：创建一个支持Ribbon的RestTemplate类
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


    public static void main(String[] args){
        SpringApplication.run(FileShareApplication.class, args);
    }
}