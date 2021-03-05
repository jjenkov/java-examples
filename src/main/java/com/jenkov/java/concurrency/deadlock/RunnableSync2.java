package com.jenkov.java.concurrency.deadlock;

import java.util.concurrent.locks.Lock;

public class RunnableSync2 implements Runnable{

    private Lock lock1 = null;
    private Lock lock2 = null;

    public RunnableSync2(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " attempting to lock Lock 2");
        synchronized(lock1) {
            System.out.println(threadName + " locked Lock 2");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                //ignore
            }

            System.out.println(threadName + " attempting to lock Lock 1");
            synchronized (lock2) {
                System.out.println(threadName + " locked Lock 2");
                //do the work - now that both locks have been acquired (locked by this thread)

            }
            System.out.println(threadName + " unlocking Lock 1");

        }
        System.out.println(threadName + " unlocking Lock 2");    }
}
