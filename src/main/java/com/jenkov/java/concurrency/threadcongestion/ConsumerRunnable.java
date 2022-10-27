package com.jenkov.java.concurrency.threadcongestion;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsumerRunnable implements Runnable {

    private BlockingQueue<String> blockingQueue = null;

    private AtomicBoolean keepRunning = new AtomicBoolean(true);

    public ConsumerRunnable(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void stop() {
        System.out.println("Stopped thread");
        this.keepRunning.set(false);
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " consumer started.");

        long objectsConsumed = 0;
        while (this.keepRunning.get()) {
            String obj = takeObjectFromQueue();
            if(obj != null) { objectsConsumed++; }
        }
        System.out.println(Thread.currentThread().getName() + " finishing up");
        while (this.blockingQueue.size() > 0) {
            String obj = takeObjectFromQueue();
            if(obj != null) { objectsConsumed++; }
        }
        System.out.println(Thread.currentThread().getName() +
                " consumer finished: " + objectsConsumed);
    }

    private String takeObjectFromQueue() {
        try {
            return blockingQueue.poll(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
