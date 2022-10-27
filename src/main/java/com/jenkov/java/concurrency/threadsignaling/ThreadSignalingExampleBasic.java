package com.jenkov.java.concurrency.threadsignaling;

public class ThreadSignalingExampleBasic {

    public static void main(String[] args) {

        Object signalObject = new Object();

        Thread waitingThread = new Thread( () -> {
           synchronized(signalObject) {
               try {
                   signalObject.wait();

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread notifyingThread = new Thread( () -> {
           synchronized(signalObject) {
               signalObject.notify();
           }
        });

        waitingThread.start();
        notifyingThread.start();

    }
}
