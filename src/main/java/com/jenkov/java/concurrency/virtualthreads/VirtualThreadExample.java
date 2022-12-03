package com.jenkov.java.concurrency.virtualthreads;

public class VirtualThreadExample {

    public static void main(String[] args) {
        Thread vThread = Thread.ofVirtual().start(() -> {
            for(int i=0; i<10; i++) {
                System.out.println("Index: " + i);
            }
        });

        try {
            vThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
