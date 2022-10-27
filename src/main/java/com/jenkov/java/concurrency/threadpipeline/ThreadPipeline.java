package com.jenkov.java.concurrency.threadpipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPipeline {

    private List<Processor> processors = new ArrayList<>();

    private BlockingQueue inputQueue  = null;
    private BlockingQueue outputQueue = null;

    public ThreadPipeline addProcessor(Processor processor){
        return this;
    }

    public BlockingQueue getInputQueue() {
        return this.inputQueue;
    }

    public BlockingQueue getOutputQueue() {
        return this.outputQueue;
    }



}
