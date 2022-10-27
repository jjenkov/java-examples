package com.jenkov.java.concurrency.threadsignaling;

public class SignalCounter {

    private int signals        = 0;

    public void doNotify() {
        synchronized (this) {
            this.signals++;
            System.out.println("Signals stored: " + this.signals);

            this.notify();
        }
    }

    public void doWait() throws InterruptedException{
        synchronized(this) {
            this.signals--;
            if(this.signals >= 0) {
                System.out.println(Thread.currentThread().getName()
                        + " - " + (this.signals+1) +
                        " signal(s) were stored. Exiting without wait().");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " calling wait()");
            this.wait();
            System.out.println(Thread.currentThread().getName() + " exited wait()");
        }
    }

}
