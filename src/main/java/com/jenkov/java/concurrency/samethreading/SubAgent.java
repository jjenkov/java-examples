package com.jenkov.java.concurrency.samethreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SubAgent implements IAgent {


    private BlockingQueue messageQueue = new ArrayBlockingQueue(16);


    private int increments = 5;

    private AtomicBoolean isTerminated = new AtomicBoolean(false);



    @Override
    public boolean isTerminated() {
        return this.isTerminated.get();
    }

    @Override
    public void continueExec() {
        System.out.println("SubAgent running: " + this.increments);

        try {
            getMessageQueue().put("" + this.increments);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.increments--;
        this.isTerminated.set(this.increments <= 0);
    }

    public BlockingQueue getMessageQueue() {
        return this.messageQueue;
    }

}
