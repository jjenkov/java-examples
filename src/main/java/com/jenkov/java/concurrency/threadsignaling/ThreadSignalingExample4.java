package com.jenkov.java.concurrency.threadsignaling;

import java.util.concurrent.Semaphore;

public class ThreadSignalingExample4 {

    public static void main(String[] args) {

        Object signalObject = new Object();

        Thread waiter = new Thread( () -> {
            synchronized(signalObject) {
                try {
                     signalObject.wait();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
        });

        Thread notifier = new Thread( () -> {
            synchronized (signalObject) {
                signalObject.notifyAll();
            }
        });

        notifier.start();
        waiter.start();

    }

}
