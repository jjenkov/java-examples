package com.jenkov.java.concurrency.compareandswap;

import java.util.concurrent.atomic.AtomicLong;

public class OptimisticLockCounter implements Counter {

    private AtomicLong count = new AtomicLong();


    public void inc() {

        boolean incSuccessful = false;
        while(!incSuccessful) {
            long value = this.count.get();  //value == 11
            long newValue = value + 1;

            incSuccessful = this.count.compareAndSet(value, newValue);
        }
    }

    public long getCount() {
        return this.count.get();
    }
}
