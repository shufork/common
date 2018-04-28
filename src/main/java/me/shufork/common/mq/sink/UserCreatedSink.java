package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserCreatedSink {
    String INPUT = "user_created_input";

    @Input(INPUT)
    SubscribableChannel input();
}
