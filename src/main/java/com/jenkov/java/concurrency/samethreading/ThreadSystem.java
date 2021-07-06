package com.jenkov.java.concurrency.samethreading;

import java.util.ArrayList;
import java.util.List;

public class ThreadSystem {

    protected List<SystemThreadRunnable> foregroundThreads = new ArrayList<>();
    protected List<SystemThreadRunnable> backgroundThreads = new ArrayList<>();

    protected int nextForegroundThreadToMoveTo = 0;
    protected int nextBackgroundThreadToMoveTo = 0;


    public SystemThreadRunnable addForegroundThread() {
        SystemThreadRunnable foregroundThread = new SystemThreadRunnable();
        this.foregroundThreads.add(foregroundThread);
        return foregroundThread;
    }

    public SystemThreadRunnable addBackgroundThread() {
        SystemThreadRunnable backgroundThread = new SystemThreadRunnable();
        this.backgroundThreads.add(backgroundThread);
        return backgroundThread;
    }

    public ThreadSystem start() {

        for(SystemThreadRunnable foregroundThread : this.foregroundThreads) {
            Thread thread = new Thread(foregroundThread);
            thread.start();
        }

        for(SystemThreadRunnable backgroundThread : this.backgroundThreads) {
            Thread thread = new Thread(backgroundThread);
            thread.start();
        }

        return this;
    }

    public ThreadSystem requestTermination() {

        for(SystemThreadRunnable foregroundThread : this.foregroundThreads) {
            foregroundThread.requestTermination();
        }

        for(SystemThreadRunnable backgroundThread : this.backgroundThreads) {
            backgroundThread.requestTermination();
        }

        return this;
    }

    public void submitToBackgroundThread(IAgent agent) throws InterruptedException {
        SystemThreadRunnable backgroundThread = this.backgroundThreads.get(this.nextBackgroundThreadToMoveTo);
        backgroundThread.submitAgent(agent);

        this.nextBackgroundThreadToMoveTo++;
        this.nextBackgroundThreadToMoveTo = this.nextBackgroundThreadToMoveTo % this.backgroundThreads.size();
    }

    public void submitToForegroundThread(IAgent agent) throws InterruptedException {
        SystemThreadRunnable foregroundThread = this.foregroundThreads.get(this.nextForegroundThreadToMoveTo);
        foregroundThread.submitAgent(agent);

        this.nextForegroundThreadToMoveTo++;
        this.nextForegroundThreadToMoveTo = this.nextForegroundThreadToMoveTo % this.foregroundThreads.size();
    }


}
