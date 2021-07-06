package com.jenkov.java.concurrency.samethreading;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A SystemThreadRunnable should be executing an IAgent instance repeatedly until one of the following conditions are true:
 *
 *
 * 1) The agent has terminated.
 *
 * 2) The SystemThreadRunnable has been signaled to terminate
 *
 *
 * If the agent being executed has terminated, the SystemThreadRunnable can pull a new IAgent instance from its
 * inbound queue of IAgents, and continue executing that IAgent instance.
 *
 */
public class SystemThreadRunnable implements Runnable {


    private BlockingQueue<IAgent> agentQueue      = new ArrayBlockingQueue<IAgent>(1024);
    private AtomicBoolean         shouldTerminate = new AtomicBoolean(false);

    public SystemThreadRunnable submitAgent(IAgent agent) throws InterruptedException {
        this.agentQueue.put(agent);
        return this;
    }

    public void requestTermination() {
        this.shouldTerminate.set(true);
    }

    @Override
    public void run() {
        while(!shouldTerminate.get()){

            try {
                IAgent nextAgent = this.agentQueue.poll(1000, TimeUnit.MILLISECONDS);

                if(nextAgent != null) {
                    while(!nextAgent.isTerminated()) {
                        nextAgent.continueExec();
                    }
                }

            } catch (InterruptedException e) {
                // ignore
                //e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Terminated");
    }


}
