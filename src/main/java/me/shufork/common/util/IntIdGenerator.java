package me.shufork.common.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IntIdGenerator {

    private final AtomicInteger id;
    private final int initVal;
    private final int maxVal;

    public static final IntIdGenerator GLOBAL = new IntIdGenerator();

    /**
     *
     * @param initVal init value
     * @param maxVal  max value(exclusive)
     */
    public IntIdGenerator(int initVal,int maxVal){
        if(initVal >= maxVal){
            throw new IllegalArgumentException("maxVal must gather than initVal");
        }
        this.initVal = initVal;
        this.maxVal  = maxVal;
        this.id = new AtomicInteger(initVal);
    }
    public IntIdGenerator(){
        this(0,Integer.MAX_VALUE);
    }
    public int getNext(){
        id.compareAndSet(maxVal,initVal);
        return id.getAndIncrement();
    }
}
