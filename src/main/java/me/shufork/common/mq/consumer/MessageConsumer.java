package me.shufork.common.mq.consumer;

public interface MessageConsumer<T> {
    void handleMessage(T message);
}
