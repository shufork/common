package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserActivatedSink {
    String INPUT = "user_activated_input";

    @Input(INPUT)
    SubscribableChannel input();
}
