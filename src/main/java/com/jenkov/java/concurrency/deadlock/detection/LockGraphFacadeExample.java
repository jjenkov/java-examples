package com.jenkov.java.concurrency.deadlock.detection;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockGraphFacadeExample {

    public static void main(String[] args) {
        withThreads();
    }

    private static void withThreads() {
        LockGraphFacade lockGraphFacade = new LockGraphFacade();

        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Runnable1DeadlockDetection runnable1 =
                new Runnable1DeadlockDetection(lockGraphFacade, lock1, lock2);
        Runnable2DeadlockDetection runnable2 =
                new Runnable2DeadlockDetection(lockGraphFacade, lock1, lock2);

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
    }
}
