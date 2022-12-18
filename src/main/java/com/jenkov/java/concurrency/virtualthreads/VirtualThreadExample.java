package com.jenkov.java.concurrency.virtualthreads;

public class VirtualThreadExample {

    public static void main(String[] args) {

        // Example 1: Create Runnable. Create and start virtual thread
        Runnable runnable = () -> {
            for(int i=0; i<10; i++) {
                System.out.println("Index: " + i);
            }
        };

        Thread vThread1 = Thread.ofVirtual().start(runnable);


        // Example 2: Create but do not start virtual thread
        Thread vThreadUnstarted = Thread.ofVirtual().unstarted(runnable);


        // Example 3: Create and start virtual thread with lambda expression
        Thread vThread = Thread.ofVirtual().start(() -> {
            for(int i=0; i<10; i++) {
                System.out.println("Index: " + i);
            }
        });


        // Example 4: How to join a virtual thread
        try {
            vThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
