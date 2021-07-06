package com.jenkov.java.concurrency.blockingqueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DrainMethods {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue  =
                new ArrayBlockingQueue<>(10);

        Collection dest = new ArrayList();

        blockingQueue.drainTo(dest);

        blockingQueue.drainTo(dest, 5);
    }
}
