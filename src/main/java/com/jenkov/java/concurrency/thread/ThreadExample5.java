package com.jenkov.java.concurrency.thread;

public class ThreadExample5 {
    public static void main(String[] args) {
        Runnable runnable = () ->{
            System.out.println("Lambada running");
            System.out.println("Lambada finished");
        };
        Thread thread = new Thread( runnable );
        thread.start();
    }
}
