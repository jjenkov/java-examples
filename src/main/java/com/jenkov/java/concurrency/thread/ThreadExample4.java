package com.jenkov.java.concurrency.thread;

public class ThreadExample4 {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable running");
                System.out.println("Runnable finished");
            }
        };
        Thread thread = new Thread( runnable );
        thread.start();
    }
}
