package com.jenkov.java.concurrency.threadpipeline;

import java.util.concurrent.BlockingQueue;

public interface Processor extends Runnable {

    public BlockingQueue setInputQueue(BlockingQueue inputQueue);

    public BlockingQueue setOutputQueue(BlockingQueue outputQueue);

    public void requestShutdown();

}
