package me.shufork.common.mq.processer;

import me.shufork.common.mq.sink.UserActivatedSink;
import me.shufork.common.mq.source.UserActivatedSource;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface UserActivatedProcessor extends UserActivatedSink,UserActivatedSource {
}
