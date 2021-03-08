package com.jenkov.java.concurrency.deadlock.detection;

import java.util.HashSet;
import java.util.Set;

public class DeadlockDetection {

    public boolean isInvolvedInDeadlock(LockNode lockNode) {
        Set<ThreadNode> involvedThreadNodes = new HashSet<>();

        while(true) {
            ThreadNode lockedByThread = lockNode.lockedBy;

            if(involvedThreadNodes.contains(lockedByThread)) {
                return true;
            }
            involvedThreadNodes.add(lockedByThread);

            if(lockedByThread.waitingFor == null) {
                return false;
            }
            lockNode = lockedByThread.waitingFor;
        }
    }
}
