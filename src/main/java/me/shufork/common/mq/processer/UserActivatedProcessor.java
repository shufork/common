package me.shufork.common.mq.processer;

import me.shufork.common.mq.sink.UserActivatedSink;
import me.shufork.common.mq.source.UserActivatedSource;

public interface UserActivatedProcessor extends UserActivatedSink,UserActivatedSource {
}
