package cn.qixqi.pan.filesharing;

import cn.qixqi.pan.filesharing.event.InBoundChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(InBoundChannel.class)
public class FileShareApplication{

    private static final Logger logger = LoggerFactory.getLogger(FileShareApplication.class);

    // 收到来自 input 通道的消息后，Spring Cloud Stream 执行此方法
    @StreamListener("inBoundFS")
    public void loggerSink(String msg){
        System.out.println("Msg: " + msg + "----------------------------------------------------------");
        logger.debug("报告长官，接收到消息：() " + msg);
    }

    public static void main(String[] args){
        SpringApplication.run(FileShareApplication.class, args);
    }
}