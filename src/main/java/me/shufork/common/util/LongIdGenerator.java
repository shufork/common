package me.shufork.common.util;

import java.util.concurrent.atomic.AtomicLong;


public class LongIdGenerator {

    private final AtomicLong id;
    private final long initVal;
    private final long maxVal;

    public static final LongIdGenerator GLOBAL = new LongIdGenerator();

    /**
     *
     * @param initVal init value
     * @param maxVal  max value(exclusive)
     */
    public LongIdGenerator(long initVal,long maxVal){
        if(initVal >= maxVal){
            throw new IllegalArgumentException("maxVal must gather than initVal");
        }
        this.initVal = initVal;
        this.maxVal  = maxVal;
        this.id = new AtomicLong(initVal);
    }
    public LongIdGenerator(){
        this(0L,Long.MAX_VALUE);
    }
    public long getNext(){
        id.compareAndSet(maxVal,initVal);
        return id.getAndIncrement();
    }
}
