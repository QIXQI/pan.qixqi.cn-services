package cn.qixqi.pan.filesharing.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InBoundChannel {

    @Input("inBoundFS")
    SubscribableChannel inBoundFS();
}
