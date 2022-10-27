package com.jenkov.java.concurrency.threadsignaling;

public class ThreadSignalingExample {

    public static void main(String[] args) {

        SignalCarrier signalCarrier = new SignalCarrier();

        Thread waiter = new Thread( () -> {
            try {
                signalCarrier.doWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread notifier = new Thread( () -> {
            signalCarrier.doNotify();
        });

        notifier.start();
        waiter.start();

    }

}
