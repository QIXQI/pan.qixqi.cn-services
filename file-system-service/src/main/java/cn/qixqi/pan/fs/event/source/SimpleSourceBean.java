package cn.qixqi.pan.fs.event.source;

import cn.qixqi.pan.fs.event.OutBoundChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleSourceBean {

    private OutBoundChannel outBoundChannel;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    @Autowired
    public SimpleSourceBean(OutBoundChannel outBoundChannel){
        this.outBoundChannel = outBoundChannel;
    }

    public void publishMsg(){
        logger.debug("发送消息到 filesharing-service");
        outBoundChannel
                .outBoundFS()
                .send(MessageBuilder.withPayload("嘿嘿").build());
    }
}
