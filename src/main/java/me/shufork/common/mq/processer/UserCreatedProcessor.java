package me.shufork.common.mq.processer;

import me.shufork.common.mq.sink.UserCreatedSink;
import me.shufork.common.mq.source.UserCreatedSource;

public interface UserCreatedProcessor extends UserCreatedSink,UserCreatedSource {
}
