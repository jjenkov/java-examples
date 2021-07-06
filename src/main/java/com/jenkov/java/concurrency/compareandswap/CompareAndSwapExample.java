package com.jenkov.java.concurrency.compareandswap;

public class CompareAndSwapExample {

    public static void main(String[] args) {

        Counter counter = new SimpleCounter(new ProblematicLock());
        //Counter counter = new SimpleCounter(new CompareAndSwapLock());
        //Counter counter = new OptimisticLockCounter();

        CountRunnable runnable1 = new CountRunnable(counter, 1_000_000);
        CountRunnable runnable2 = new CountRunnable(counter, 1_000_000);

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        thread1.start();
        thread2.start();



    }
}
