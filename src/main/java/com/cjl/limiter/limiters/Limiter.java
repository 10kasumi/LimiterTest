package com.cjl.limiter.limiters;

public abstract class Limiter {

    final int qps;

    Limiter(int qps){
        this.qps = qps;
    }

    public abstract boolean tryAcquire();
}
