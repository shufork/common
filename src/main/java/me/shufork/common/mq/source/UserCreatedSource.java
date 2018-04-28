package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserCreatedSource {
    String OUTPUT = "user_created_output";

    @Output(OUTPUT)
    MessageChannel output();
}
