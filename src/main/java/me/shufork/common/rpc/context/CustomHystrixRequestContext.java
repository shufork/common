package me.shufork.common.rpc.context;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

public final class CustomHystrixRequestContext {

    private static final CustomHystrixRequestContext context = new CustomHystrixRequestContext();

    private static final HystrixRequestVariableDefault<String> userIdVariable = new HystrixRequestVariableDefault<>();

    public static CustomHystrixRequestContext getInstance() {
        return context;
    }

    public synchronized void setUserId(String uid) {
        userIdVariable.set(uid);
    }

    public synchronized String getUserId() {
        return userIdVariable.get();
    }
}
