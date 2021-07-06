package com.jenkov.java.concurrency.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class EnqueueMethods {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue  =
                new ArrayBlockingQueue<>(3);

        // put() will block until there is space
        // inside the BlockingQueue for the element
        try {
            blockingQueue.put("1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // add will throw IllegalStateException if
        // no space available in BlockingQueue
        try{
            blockingQueue.add("2");
        } catch(IllegalStateException e) {
            // no space inside BlockingQueue
        }

        // offer() returns false if no space
        boolean wasEnqueued = blockingQueue.offer("3");

        // offer(o, time, TimeUnit) blocks for time if no space,
        // then returns false if still no space.
        try {
            boolean wasEnqueued2 =
                    blockingQueue.offer("3", 1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
