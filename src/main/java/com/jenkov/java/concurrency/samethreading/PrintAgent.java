package com.jenkov.java.concurrency.samethreading;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintAgent implements IAgent {

    private String        agentName     = null;
    private AtomicBoolean isTerminated  = new AtomicBoolean(false);

    private ThreadSystem  threadSystem  = null;

    private AtomicInteger agentState    = new AtomicInteger(0);

    public PrintAgent() {}
    public PrintAgent(String agentName, ThreadSystem threadSystem) {
        this.agentName    = agentName;
        this.threadSystem = threadSystem;
    }

    @Override
    public boolean isTerminated() {
        return this.isTerminated.get();
    }

    @Override
    public void continueExec() {
        String threadPlusAgentName = Thread.currentThread().getName() + " " + this.agentName;

        System.out.println(threadPlusAgentName + " running in foreground thread." );

        SubAgent subAgent = new SubAgent();
        try {
            this.threadSystem.submitToBackgroundThread(subAgent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0; i<10; i++) {
            System.out.println(threadPlusAgentName + " Print " + i);

            sleep(500);
        }

        while(subAgent.getMessageQueue().size() > 0) {
            try {
                String message = (String) subAgent.getMessageQueue().take();
                System.out.println(threadPlusAgentName + " from SubAgent: " + message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.isTerminated.set(true);
    }

    private void sleep(int sleepIntervalMillis) {
        try {
            Thread.sleep(sleepIntervalMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
