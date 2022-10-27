package com.jenkov.java.concurrency.threadsignaling;

public class ThreadSignalingExample3 {

    public static void main(String[] args) {

        SignalCounter signalCounter = new SignalCounter();

        Thread waiter = new Thread( () -> {
            for(int i=0; i<100; i++) {
                try {
                    signalCounter.doWait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread notifier = new Thread( () -> {
            for(int i=0; i<100; i++) {
                signalCounter.doNotify();
            }
        });

        notifier.start();
        waiter.start();

    }

}
