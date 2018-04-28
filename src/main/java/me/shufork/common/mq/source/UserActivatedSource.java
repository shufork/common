package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UserActivatedSource {
    String OUTPUT = "user_activated_output";

    @Output(OUTPUT)
    MessageChannel output();
}
