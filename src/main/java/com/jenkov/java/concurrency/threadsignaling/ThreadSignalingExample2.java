package com.jenkov.java.concurrency.threadsignaling;

public class ThreadSignalingExample2 {

    public static void main(String[] args) {

        SignalHolder signalHolder = new SignalHolder();

        Thread waiter = new Thread( () -> {
            try {
                signalHolder.doWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread notifier = new Thread( () -> {
            signalHolder.doNotify();
        });


        waiter.start();
        notifier.start();

    }

}
