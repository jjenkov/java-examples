package com.jenkov.java.concurrency.threadsignaling;

public class SpuriousWakeupSignalGuard {

    Object myMonitorObject = new Object();
    boolean wasSignalled = false;

    public void doNotify(){
        synchronized(myMonitorObject){
            wasSignalled = true;
            myMonitorObject.notify();
        }
    }

    public void doWait() throws InterruptedException {
        synchronized(myMonitorObject){
            while(!wasSignalled){
                myMonitorObject.wait();
            }
            //clear signal and continue running.
            wasSignalled = false;
        }
    }

}
