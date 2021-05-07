package cn.qixqi.pan.fs.event;

import org.springframework.messaging.MessageChannel;
import org.springframework.cloud.stream.annotation.Output;

public interface OutBoundChannel {

    @Output("outBoundFS")
    MessageChannel outBoundFS();
}
