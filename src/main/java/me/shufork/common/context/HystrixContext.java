package me.shufork.common.context;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

public class HystrixContext {

    private static final HystrixContext context = new HystrixContext();

    private static final HystrixRequestVariableDefault<String> userIdVariable = new HystrixRequestVariableDefault<>();

    public static HystrixContext getInstance() {
        return context;
    }

    public synchronized void setUserId(String _userId) {
        userIdVariable.set(_userId);
    }

    public synchronized String getUserId() {
        return userIdVariable.get();
    }

}
