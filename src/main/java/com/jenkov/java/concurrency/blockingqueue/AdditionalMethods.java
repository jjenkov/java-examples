package com.jenkov.java.concurrency.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AdditionalMethods {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue  =
                new ArrayBlockingQueue<>(3);

        int size     = blockingQueue.size();

        int capacity = blockingQueue.remainingCapacity();

        boolean containsElement =
                blockingQueue.contains("1");
    }
}
