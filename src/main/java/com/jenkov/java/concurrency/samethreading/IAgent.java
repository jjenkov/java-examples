package com.jenkov.java.concurrency.samethreading;

public interface IAgent {


    //todo requestTermination() ??


    public boolean isTerminated();

    public void continueExec();

}
