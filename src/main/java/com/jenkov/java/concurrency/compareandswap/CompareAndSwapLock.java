package com.jenkov.java.concurrency.compareandswap;

import java.util.concurrent.atomic.AtomicBoolean;

public class CompareAndSwapLock implements MyLock{

    private AtomicBoolean atomicLocked = new AtomicBoolean(false);


    public void unlock() {
        this.atomicLocked.set(false);
    }

    public void lock() {
        while(!this.atomicLocked.compareAndSet(false, true)) {
            // busy wait - until compareAndSet() succeeds
        }


    }


}
