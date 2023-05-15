package com.jenkov.java.concurrency.thread;

public class ThreadExample9 {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            while(true){
                sleep(1000);
                System.out.println("Running");
            }
        };

        Thread thread = new Thread( runnable );
        thread.setDaemon(true);//set before start
        thread.start();
        sleep(3100);
        System.out.println("3s passed");
    }

    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
