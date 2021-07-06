package com.jenkov.java.concurrency.compareandswap;

public class CountRunnable implements Runnable {

    private Counter counter  = null;
    private long iterations = 0;

    public CountRunnable(Counter counter, long iterations) {
        this.counter = counter;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for(int i=0; i<this.iterations; i++) {
            this.counter.inc();
        }

        System.out.println(this.counter.getCount());
    }

}
