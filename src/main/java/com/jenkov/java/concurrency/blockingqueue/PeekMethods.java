package com.jenkov.java.concurrency.blockingqueue;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PeekMethods {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue  =
                new ArrayBlockingQueue<>(3);

        String element1 = blockingQueue.peek();

        try {
            String element2 = blockingQueue.element();
        } catch(NoSuchElementException e) {
            System.out.println("BlockingQueue is empty");
        }


    }
}
