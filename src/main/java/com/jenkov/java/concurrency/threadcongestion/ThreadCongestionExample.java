package com.jenkov.java.concurrency.threadcongestion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadCongestionExample {

    public static void main(String[] args) {
        int objectsToProduce = 1_000_000;

        BlockingQueue<String> blockingQueue     =
                new ArrayBlockingQueue<String>(objectsToProduce);

        ConsumerRunnable[]    consumerRunnables =
                new ConsumerRunnable[3];

        synchronized (ThreadCongestionExample.class){
            for(int i=0; i<consumerRunnables.length; i++) {
                consumerRunnables[i] = new ConsumerRunnable(blockingQueue);
                Thread consumingThread = new Thread(consumerRunnables[i]);
                consumingThread.start();
            }
        }

        Thread producingThread = new Thread(() -> {
            for(int i=0; i<objectsToProduce; i++) {
                try {
                    blockingQueue.put("" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("All objects produced!");
            System.out.println("  => produced " + objectsToProduce);
            synchronized (ThreadCongestionExample.class) {
                for (int i = 0; i < consumerRunnables.length; i++) {
                    consumerRunnables[i].stop();
                }
            }
        });
        producingThread.start();


    }
}
