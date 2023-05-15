package com.jenkov.java.concurrency.thread;

public class ThreadExample10 {
    public static void main(String[] args) {
        Runnable runnable = () ->{
            for (int i = 0; i < 5; i++) {
                sleep(1000);
                System.out.println("Running");
            }
        };

        Thread thread = new Thread( runnable );
        thread.setDaemon(true);
        thread.start();

//        try{
//            thread.join();
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
    }


    public static void sleep(long millis){
        try{
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
