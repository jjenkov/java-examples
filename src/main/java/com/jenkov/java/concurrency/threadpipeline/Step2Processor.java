package com.jenkov.java.concurrency.threadpipeline;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Step2Processor implements Processor {

    private BlockingQueue inputQueue = new ArrayBlockingQueue(10);

    private BlockingQueue outputQueue = null;

    private volatile boolean shutdownRequested = false;

    private volatile Thread executingThread = null;


    @Override
    public BlockingQueue setInputQueue(BlockingQueue inputQueue) {
        this.inputQueue = inputQueue;
        return this.inputQueue;
    }

    @Override
    public BlockingQueue setOutputQueue(BlockingQueue outputQueue) {
        this.outputQueue = outputQueue;
        return this.outputQueue;
    }

    @Override
    public void requestShutdown() {
        this.shutdownRequested = true;
        this.executingThread.interrupt();
    }

    @Override
    public void run() {

        this.executingThread = Thread.currentThread();
        while(!this.shutdownRequested){
            Object input = null;
            try {
                input = this.inputQueue.take();

                Object output = input.toString().substring(0, 7);

                this.outputQueue.put(output);

                //System.out.println("Step 2: " + input + " => " + output);
            } catch (InterruptedException e) {
                if(!this.shutdownRequested){
                    System.out.println("Step 2: Failed for input " + input);
                    e.printStackTrace();
                }
            }
        }
    }
}
