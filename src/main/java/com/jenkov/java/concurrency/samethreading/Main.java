package com.jenkov.java.concurrency.samethreading;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // *) Agents transferring themselves or other agents to a different thread
        //    - ThreadSystem.transferToForegroundThread(IAgent)
        //    - ThreadSystem.transferToBackgroundThread(IAgent)

        // *) Sub agents releasing messages / results to a parent agent
        //    - IAgent.getResultQueue()

        // --------------------------------------------------------------------------

        // *) Agent switcher ... like a one-off task executor switching between tasks
        //    - switcher.addAgent(IAgent agent)
        //    - switcher.continue(increments)

        // *) Agent switcher - prioritized - so some agents are allowed to execute a higher number of increments than others.



        ThreadSystem threadSystem = new ThreadSystem();

        threadSystem.addForegroundThread().submitAgent(new PrintAgent("PA 0", threadSystem));
        threadSystem.addForegroundThread().submitAgent(new PrintAgent("PA 1", threadSystem));

        threadSystem.addBackgroundThread();

        threadSystem.start();

        Thread.sleep(1000);

        threadSystem.requestTermination();
    }

}
